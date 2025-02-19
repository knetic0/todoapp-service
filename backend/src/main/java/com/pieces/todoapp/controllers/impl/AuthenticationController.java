package com.pieces.todoapp.controllers.impl;

import com.pieces.todoapp.controllers.IAuthenticationController;
import com.pieces.todoapp.core.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping
@RestController
public class AuthenticationController implements IAuthenticationController {

    @Override
    public Result<?> tokenCheck(HttpServletRequest request) {
        return new Result<>(true, new Date(System.currentTimeMillis()));
    }

}
