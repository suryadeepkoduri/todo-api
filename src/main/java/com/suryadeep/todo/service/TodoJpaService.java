package com.suryadeep.todo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.suryadeep.todo.model.TodolistTable;
import com.suryadeep.todo.repository.TodoJpaRepository;

@Service
public class TodoJpaService {       
    private final TodoJpaRepository repository;
    
    public TodoJpaService(TodoJpaRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<TodolistTable> update(TodolistTable todo,int id) {
        TodolistTable newTodo = repository.findById(id).orElse(null);

        if(newTodo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        newTodo.setTodo(todo.getTodo());
        newTodo.setStatus(todo.getStatus());
        newTodo.setPriority(todo.getPriority());
        repository.save(newTodo);

        return new ResponseEntity<>(newTodo,HttpStatus.OK);
    }

    public List<TodolistTable> findAll() {
        return repository.findAll();
    }

    public ResponseEntity<String> delete(int id) {
        TodolistTable todo = repository.findById(id).orElse(null);

        if(todo == null)
            return new ResponseEntity<>("Todo not found",HttpStatus.NOT_FOUND);

        repository.delete(todo);
        return new ResponseEntity<>("Todo deleted",HttpStatus.OK);
    }

    public ResponseEntity<TodolistTable> getById(int id) {
        TodolistTable todo = repository.findById(id).orElse(null);

        if(todo == null) {
            return new ResponseEntity<>(todo,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(todo,HttpStatus.OK);
    }

    public TodolistTable addTodo(TodolistTable todo) {
        return repository.save(todo);
    }
}
