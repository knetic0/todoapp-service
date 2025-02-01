package com.pieces.todoapp.business.concretes;

import com.pieces.todoapp.business.abstracts.ITodoService;
import com.pieces.todoapp.core.mapper.IModelMapperService;
import com.pieces.todoapp.core.result.Result;
import com.pieces.todoapp.dto.request.CreateTodoRequest;
import com.pieces.todoapp.dto.request.UpdateTodoRequest;
import com.pieces.todoapp.dto.response.GetAllTodosResponse;
import com.pieces.todoapp.entity.Todo;
import com.pieces.todoapp.entity.User;
import com.pieces.todoapp.repository.ITodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService implements ITodoService {

    private final ITodoRepository todoRepository;
    private final IModelMapperService modelMapperService;

    public TodoService(
            ITodoRepository todoRepository,
            IModelMapperService modelMapperService
    ){
        this.todoRepository = todoRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result<List<GetAllTodosResponse>> getAll(int id) {
        List<Todo> todos = todoRepository.findAllByUserId(id);

        List<GetAllTodosResponse> response = todos
                .stream()
                .map(todo -> this.modelMapperService
                        .forResponse()
                        .map(todo, GetAllTodosResponse.class))
                .collect(Collectors.toList());

        return new Result<>(true, response);
    }

    @Override
    public void delete(int id) {
        this.todoRepository.deleteById(id);
    }

    @Override
    public void create(CreateTodoRequest request, User user) {
        Todo todo = request.toTodo(user);
        todoRepository.save(todo);

    }

    @Override
    public void update(UpdateTodoRequest request, int id) {
        Todo todo = todoRepository.findById(id).get();
        todo.setContent(request.getContent());
        todo.setTitle(request.getTitle());
        todo.setDueTime(request.getDueTime());
        todoRepository.save(todo);

    }
}
