package com.pieces.todoapp.business.rules;

import com.pieces.todoapp.business.constants.Messages;
import com.pieces.todoapp.core.exeption.BusinessExeption;
import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.entity.User;
import com.pieces.todoapp.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public Result<User> checkIfUserExists(String username, String email) {
        Optional<User> user = this.userRepository.findByEmailOrUsername(email, username);
        return user.map(value -> new Result<>(true, Messages.UserExists, value)).orElseGet(() -> new Result<>(false, Messages.UserDoesNotExist));
    }

    public Result<User> checkIfUserExistsAndComparePassword(String username, String password) {
        Optional<User> user = this.userRepository.findByEmailOrUsername(username, username);

        if(user.isPresent()) {
            User u = user.get();
            if(passwordEncoder.matches(password, u.getPassword())){
                return new Result<>(true, u);
            }
            return new Result<>(false, Messages.IncorrectPassword, u);
        }

        return new Result<>(false, Messages.UserDoesNotExist);
    }
}
