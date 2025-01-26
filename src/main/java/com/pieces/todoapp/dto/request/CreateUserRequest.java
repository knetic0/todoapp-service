package com.pieces.todoapp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;
}
