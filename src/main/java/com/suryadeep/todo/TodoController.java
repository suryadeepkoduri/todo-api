package com.suryadeep.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class TodoController {

    @Autowired
    TodoJpaRepository repository;
    
    @GetMapping("/todos")
    public List<TodolistTable> getAllTodos() {
        return repository.findAll();
    }

    @PostMapping("/todos")
    public TodolistTable newTodo(@RequestBody TodolistTable newTodo) {
        return repository.save(newTodo);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Optional<TodolistTable>> getTodoById(@PathVariable int id) {
        Optional<TodolistTable> todo = repository.findById(id);
        if (todo.isEmpty()) {
            return new ResponseEntity<>(todo,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(todo,HttpStatus.OK);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id){ 
        Optional<TodolistTable> todo = repository.findById(id);
        if(todo.isEmpty()) {
            return new ResponseEntity<>("TODO not found",HttpStatus.NOT_FOUND);
        }else {
            repository.deleteById(id);
            return new ResponseEntity<>("TODO deleted",HttpStatus.OK);
        }
    }
    
    @PutMapping("/todos/{id}")
    public ResponseEntity<TodolistTable> updateTodo(@PathVariable int id, @RequestBody TodolistTable todo) {
        TodolistTable newTodo = repository.findById(id).orElse(null);

        if (newTodo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newTodo.setTodo(todo.getTodo());
        newTodo.setStatus(todo.getStatus());
        newTodo.setPriority(todo.getPriority());

        return new ResponseEntity<>(repository.save(newTodo),HttpStatus.OK);
    } 
   
}
