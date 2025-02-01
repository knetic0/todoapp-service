package com.pieces.todoapp.controllers;

import com.pieces.todoapp.controllers.constants.RequestMappingField;
import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.dto.request.CreateTodoRequest;
import com.pieces.todoapp.dto.response.GetAllTodosResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ITodoController {
    @GetMapping(RequestMappingField.GET_TODOS_MAP)
    Result<List<GetAllTodosResponse>> getAll(HttpServletRequest req);

    @PostMapping(RequestMappingField.DELETE_TODO_MAP)
    void delete(@PathVariable int id);

    @PostMapping(RequestMappingField.CREATE_TODO_MAP)
    void create(@RequestBody CreateTodoRequest request, HttpServletRequest req);
}
