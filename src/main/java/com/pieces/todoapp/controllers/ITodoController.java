package com.pieces.todoapp.controllers;

import com.pieces.todoapp.controllers.constants.RequestMappingField;
import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.dto.response.GetAllTodosResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface ITodoController {
    @PostMapping(RequestMappingField.GET_TODOS_MAP)
    Result<List<GetAllTodosResponse>> getAll(HttpServletRequest req);

    @PostMapping(RequestMappingField.DELETE_TODO_MAP)
    void delete(@PathVariable int id);
}
