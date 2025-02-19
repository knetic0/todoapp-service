package com.pieces.todoapp.business.abstracts;

import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.dto.request.CreateUserRequest;
import com.pieces.todoapp.dto.request.UserLoginRequest;
import com.pieces.todoapp.dto.response.CreateUserResponse;
import com.pieces.todoapp.dto.response.UserLoginResponse;

public interface IUserService {
    Result<CreateUserResponse> create(CreateUserRequest createUserRequest);
    Result<UserLoginResponse> login(UserLoginRequest userLoginRequest);
}
