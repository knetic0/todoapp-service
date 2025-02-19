package com.pieces.todoapp.controllers.impl;

import com.pieces.todoapp.business.abstracts.IUserService;
import com.pieces.todoapp.controllers.IUserController;
import com.pieces.todoapp.controllers.constants.RequestMappingField;
import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.dto.request.CreateUserRequest;
import com.pieces.todoapp.dto.request.UserLoginRequest;
import com.pieces.todoapp.dto.response.CreateUserResponse;
import com.pieces.todoapp.dto.response.UserLoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@AllArgsConstructor
public class UserControllerImpl implements IUserController {
    private final IUserService userService;

    @Override
    public Result<CreateUserResponse> create(@RequestBody CreateUserRequest createUserRequest) {
        return userService.create(createUserRequest);
    }

    @Override
    public Result<UserLoginResponse> login(UserLoginRequest userLoginRequest) {
        return userService.login(userLoginRequest);
    }
}
