package com.pieces.todoapp.business.concretes;

import com.pieces.todoapp.business.constants.Messages;
import com.pieces.todoapp.business.rules.UserBusinessRules;
import com.pieces.todoapp.business.abstracts.IUserService;
import com.pieces.todoapp.core.mapper.IModelMapperService;
import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.core.security.jwt.ITokenHelper;
import com.pieces.todoapp.dto.request.CreateUserRequest;
import com.pieces.todoapp.dto.request.UserLoginRequest;
import com.pieces.todoapp.dto.response.CreateUserResponse;
import com.pieces.todoapp.dto.response.UserLoginResponse;
import com.pieces.todoapp.entity.User;
import com.pieces.todoapp.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IModelMapperService modelMapperService;
    private final PasswordEncoder passwordEncoder;
    private final UserBusinessRules userBusinessRules;
    private final ITokenHelper tokenHelper;

    @Override
    public Result<CreateUserResponse> create(CreateUserRequest createUserRequest) {
        Result<User> result = userBusinessRules.checkIfUserExists(createUserRequest.getUsername(), createUserRequest.getEmail());
        if(result.isSuccess()){
            return new Result<>(false, result.getMessage());
        }
        User user = modelMapperService.forRequest().map(createUserRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new Result<>(true, Messages.UserRegistered);
    }

    @Override
    public Result<UserLoginResponse> login(UserLoginRequest userLoginRequest) {
        Result<User> result = userBusinessRules.checkIfUserExistsAndComparePassword(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        if(result.isSuccess()) {
            return new Result<>(true, new UserLoginResponse(tokenHelper.generateToken(result.getData())));
        }
        return new Result<>(false, result.getMessage());
    }
}
