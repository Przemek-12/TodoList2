package com.todo.todoList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    public List<ToDoListDTO> add(ToDoList todo) {
        toDoListRepository.save(todo);
        return getList(todo.getUser().getId());
    }

    public List<ToDoListDTO> delete(Long userId, Long todoId) {
        toDoListRepository.deleteById(todoId);
        return getList(userId);
    }

    public List<ToDoListDTO> update(ToDoList todo) {
        toDoListRepository.save(todo);
        return getList(todo.getUser().getId());
    }

    public List<ToDoListDTO> getList(Long userId) {
        return toDoListRepository.findByUserId(userId).stream().map(todo -> mapToToDoListDTO(todo))
                .collect(Collectors.toList());
    }

    private ToDoListDTO mapToToDoListDTO(ToDoList todo) {
        return ToDoListDTO.builder().id(todo.getId()).date(todo.getDate()).text(todo.getText())
                .userId(todo.getUser().getId()).build();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ToDoListDTO {
        private Long id;
        private LocalDateTime date;
        private String text;
        private Long userId;
    }

}
