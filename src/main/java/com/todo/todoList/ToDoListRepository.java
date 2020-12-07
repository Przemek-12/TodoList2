package com.todo.todoList;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

    List<ToDoList> findByUserId(Long userId);

    @Override
    Optional<ToDoList> findById(Long id);
}
