package com.pieces.todoapp.repository;

import com.pieces.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByUserId(int id);
}
