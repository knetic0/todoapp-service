package com.pieces.todoapp.business.concretes;

import com.pieces.todoapp.business.UserBusinessRules;
import com.pieces.todoapp.business.abstracts.IUserService;
import com.pieces.todoapp.core.mapper.IModelMapperService;
import com.pieces.todoapp.core.security.jwt.ITokenHelper;
import com.pieces.todoapp.dto.request.CreateUserRequest;
import com.pieces.todoapp.dto.request.UserLoginRequest;
import com.pieces.todoapp.entity.User;
import com.pieces.todoapp.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private IUserRepository userRepository;
    private IModelMapperService modelMapperService;
    private PasswordEncoder passwordEncoder;
    private UserBusinessRules userBusinessRules;
    private ITokenHelper tokenHelper;

    @Override
    public void create(CreateUserRequest createUserRequest) {
        this.userBusinessRules.checkIfUserExists(createUserRequest.getUserName(), createUserRequest.getEmail());
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public String login(UserLoginRequest userLoginRequest) {
        User user = this.userBusinessRules.checkIfUserExistsAndComparePassword(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        if(user != null) {
            String token = tokenHelper.generateToken(user);
            return token;
        }
        return null;
    }


}
