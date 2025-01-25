package com.pieces.todoapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private boolean isDone = false;

    private Date createdTime;

    private Date dueTime;

    private Date updatedTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
