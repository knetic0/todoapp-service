package com.pieces.todoapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    private Date createdTime;

    private Date dueTime;

    @UpdateTimestamp
    private Date updatedTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(){}
    public Todo(
            String title,
            String content,
            User user,
            Date dueTime
    ){
        this.title = title;
        this.content = content;
        this.user = user;
        this.dueTime = dueTime;
    }
}
