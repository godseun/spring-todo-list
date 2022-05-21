package com.godseun.todolistspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.godseun.todolistspring.dto.ResponseDTO;
import com.godseun.todolistspring.dto.TodoDTO;
import com.godseun.todolistspring.model.TodoEntity;
import com.godseun.todolistspring.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {

  @Autowired
  private TodoService service;

  @RequestMapping("/test")
  public ResponseEntity<?> testTodo() {
    String str = service.testService();
    List<String> list = new ArrayList<>();
    list.add(str);
    ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
    return ResponseEntity.ok().body(response);
  }

  @GetMapping
  public ResponseEntity<?> retrieveTodoList() {
    String temporaryUserId = "temp-id";
    List<TodoEntity> entities = service.retrieve(temporaryUserId);
    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
    return ResponseEntity.ok().body(response);
  }

  @PostMapping
  public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDto) {
    try {
      String temporaryUserId = "temp-id";
      TodoEntity todoEntity = TodoDTO.toEntity(todoDto);
      todoEntity.setId(null);
      todoEntity.setUserId(temporaryUserId);

      List<TodoEntity> entities = service.create(todoEntity);
      List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      String error = e.getMessage();
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  @PutMapping
  public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
    String temporaryUserId = "temp-id";

    TodoEntity entity = TodoDTO.toEntity(dto);
    entity.setUserId(temporaryUserId);

    List<TodoEntity> entities = service.update(entity);
    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
    return ResponseEntity.ok().body(response);
  }
}
