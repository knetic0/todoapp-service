package com.pieces.todoapp.controllers;

import com.pieces.todoapp.controllers.constants.RequestMappingField;
import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.dto.request.CreateUserRequest;
import com.pieces.todoapp.dto.response.CreateUserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserController {
    @PostMapping(RequestMappingField.CREATE_USER_MAP)
    Result<CreateUserResponse> create(@RequestBody CreateUserRequest createUserRequest);
}
