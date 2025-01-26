package com.pieces.todoapp.dto.response;

import lombok.Data;

@Data
public class UserLoginResponse {
    private String token;

    public UserLoginResponse() {}

    public UserLoginResponse(String token) {
        this.token = token;
    }
}
