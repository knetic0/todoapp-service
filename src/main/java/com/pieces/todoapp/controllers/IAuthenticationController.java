package com.pieces.todoapp.controllers;

import com.pieces.todoapp.controllers.constants.RequestMappingField;
import com.pieces.todoapp.core.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;


public interface IAuthenticationController {
    @PostMapping(RequestMappingField.AUTHENTICATE_MAP)
    Result<?> tokenCheck(HttpServletRequest request);
}
