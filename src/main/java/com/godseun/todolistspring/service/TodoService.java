package com.godseun.todolistspring.service;

import java.util.List;
import java.util.Optional;

import com.godseun.todolistspring.model.TodoEntity;
import com.godseun.todolistspring.persistence.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

  public List<TodoEntity> update(final TodoEntity todoEntity) {

    validate(todoEntity);

    final Optional<TodoEntity> original = repository.findById(todoEntity.getId());

    original.ifPresent(todo -> {
      todo.setTitle(todoEntity.getTitle());
      todo.setDone(todoEntity.isDone());

      repository.save(todoEntity);
    });

    return retrieve(todoEntity.getUserId());
  }

  public List<TodoEntity> retrieve(final String userId) {
    return repository.findByUserId(userId);
  }

  public List<TodoEntity> create(final TodoEntity todoEntity) {

    validate(todoEntity);

    repository.save(todoEntity);
    log.info("Entity ID : {} is saved.", todoEntity.getId());

    return repository.findByUserId(todoEntity.getUserId());
  }

  private void validate(final TodoEntity todoEntity) {
    if (null == todoEntity) {
      log.warn("Entity cannot be null.");
      throw new RuntimeException("Entity cannot be null");
    }

    if (null == todoEntity.getUserId()) {
      log.warn("Unknown user.");
      throw new RuntimeException("Unknown user.");
    }
  }
}
