package com.godseun.todolistspring.persistence;

import com.godseun.todolistspring.model.TodoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

}
