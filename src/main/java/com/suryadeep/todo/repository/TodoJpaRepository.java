package com.suryadeep.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suryadeep.todo.model.TodolistTable;


@Repository
public interface TodoJpaRepository extends JpaRepository<TodolistTable, Integer> {

    
}
