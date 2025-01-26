package com.pieces.todoapp.business;

import com.pieces.todoapp.business.constants.Messages;
import com.pieces.todoapp.core.exeption.BusinessExeption;
import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.entity.User;
import com.pieces.todoapp.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void checkIfUserExists(String username, String email) {
        if(userRepository.existsByUsername(username)) {
            throw new BusinessExeption(Messages.UserExists);
        }
        if(userRepository.existsByEmail(email)) {
            throw new BusinessExeption(Messages.UserExists);
        }
    }

    public Result<User> checkIfUserExistsAndComparePassword(String username, String password) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new BusinessExeption(Messages.UserDoesNotExist));
        if(passwordEncoder.matches(password, user.getPassword())){
            return new Result<>(true, user);
        }
        return new Result<>(false, Messages.IncorrectPassword, user);
    }
}
