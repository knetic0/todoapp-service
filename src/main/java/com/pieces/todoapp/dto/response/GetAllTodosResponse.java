package com.pieces.todoapp.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetAllTodosResponse {
    private String title;

    private String content;

    private boolean isDone;

    private Date createdTime;

    private Date dueTime;

    private Date updatedTime;
}
