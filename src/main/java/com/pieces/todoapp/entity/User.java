package com.pieces.todoapp.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Table(name = "users")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private boolean isEnabled = true;

    private Date createdTime;

    @OneToMany(mappedBy = "user")
    private List<Todo> todo;
}
