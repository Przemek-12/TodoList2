package com.todo.todoList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todoList.ToDoListService.ToDoListDTO;

@RestController
@RequestMapping("todo")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping
    public List<ToDoListDTO> getList(@RequestParam Long userId) {
        return toDoListService.getList(userId);
    }

    @PostMapping
    public List<ToDoListDTO> add(@RequestBody ToDoList todo) {
        return toDoListService.add(todo);
    }

    @PutMapping
    public List<ToDoListDTO> update(@RequestBody ToDoList todo) {
        return toDoListService.update(todo);
    }

    @DeleteMapping
    public List<ToDoListDTO> delete(@RequestParam Long userId, @RequestParam Long todoId) {
        return toDoListService.delete(userId, todoId);
    }
}
