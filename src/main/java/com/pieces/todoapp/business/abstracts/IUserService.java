package com.pieces.todoapp.business.abstracts;

import com.pieces.todoapp.dto.request.CreateUserRequest;
import com.pieces.todoapp.dto.request.UserLoginRequest;

public interface IUserService {
    void create(CreateUserRequest createUserRequest);
    String login(UserLoginRequest userLoginRequest);
}
