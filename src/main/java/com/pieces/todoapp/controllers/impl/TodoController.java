package com.pieces.todoapp.controllers.impl;

import com.pieces.todoapp.business.abstracts.ITodoService;
import com.pieces.todoapp.controllers.ITodoController;
import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.dto.request.CreateTodoRequest;
import com.pieces.todoapp.dto.response.GetAllTodosResponse;
import com.pieces.todoapp.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TodoController implements ITodoController {

    private final ITodoService todoService;

    public TodoController(
            ITodoService todoService
    ){
        this.todoService = todoService;
    }

    public void create(@RequestBody CreateTodoRequest request, HttpServletRequest req){
        User user = (User) req.getAttribute("user");
        todoService.create(request, user);

    }

    @Override
    public Result<List<GetAllTodosResponse>> getAll(HttpServletRequest req) {
        User user = (User) req.getAttribute("user");
        return todoService.getAll(user.getId());
    }

    @Override
    public void delete(int id) {
        todoService.delete(id);
    }
}
