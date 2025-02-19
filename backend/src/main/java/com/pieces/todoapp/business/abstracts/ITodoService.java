package com.pieces.todoapp.business.abstracts;

import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.dto.request.CreateTodoRequest;
import com.pieces.todoapp.dto.request.UpdateTodoRequest;
import com.pieces.todoapp.dto.response.GetAllTodosResponse;
import com.pieces.todoapp.entity.User;

import java.util.List;

public interface ITodoService {

    Result<List<GetAllTodosResponse>> getAll(int id);
    void delete(int id);
    void create(CreateTodoRequest request, User user);
    void update(UpdateTodoRequest request, int id);
}
