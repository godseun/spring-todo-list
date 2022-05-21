package com.godseun.todolistspring.service;

import com.godseun.todolistspring.model.TodoEntity;
import com.godseun.todolistspring.persistence.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  @Autowired
  private TodoRepository repository;

  public String testService() {
    TodoEntity entity = TodoEntity.builder().title("first todo item").build();
    repository.save(entity);
    TodoEntity saveEntity = repository.findById(entity.getId()).get();
    return saveEntity.getTitle();
  }
}
