package com.pieces.todoapp.business;

import com.pieces.todoapp.core.exeption.BusinessExeption;
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
        this.userRepository.existsByUsername(username).orElseThrow(() -> new BusinessExeption("kullanici mevcut"));
        this.userRepository.existsByEmail(email).orElseThrow(() -> new BusinessExeption("mail mevcut"));
    }

    public User checkIfUserExistsAndComparePassword(String username, String password) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new BusinessExeption("kullanici mevcut degil"));
        if(passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        return null;
    }
}
