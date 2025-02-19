package com.pieces.todoapp.dto.request;

import com.pieces.todoapp.entity.Todo;
import com.pieces.todoapp.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateTodoRequest {
    private String title;
    private String content;
    private Date dueTime;

    public Todo toTodo(User user){
        return new Todo(title, content, user, dueTime);
    }
}
