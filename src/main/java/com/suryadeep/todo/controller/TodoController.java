package com.suryadeep.todo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suryadeep.todo.model.TodolistTable;
import com.suryadeep.todo.service.TodoJpaService;

@RestController
public class TodoController {

    TodoJpaService service;

    public TodoController(TodoJpaService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public List<TodolistTable> getAllTodos() {
        return service.findAll();
    }

    @PostMapping("/todos")
    public TodolistTable newTodo(@RequestBody TodolistTable newTodo) {
        return service.addTodo(newTodo);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodolistTable> getTodoById(@PathVariable int id) {
        return service.getById(id);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id) {
        return service.delete(id);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodolistTable> updateTodo(@PathVariable int id, @RequestBody TodolistTable todo) {
        return service.update(todo, id);
    }

}
