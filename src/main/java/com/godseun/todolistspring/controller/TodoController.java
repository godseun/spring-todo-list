package com.godseun.todolistspring.controller;

import java.util.ArrayList;
import java.util.List;

import com.godseun.todolistspring.dto.ResponseDTO;
import com.godseun.todolistspring.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
