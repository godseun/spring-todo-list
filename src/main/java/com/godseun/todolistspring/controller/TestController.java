package com.godseun.todolistspring.controller;

import com.godseun.todolistspring.dto.TestRequestBodyDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

  @GetMapping
  public String testController() {
    return "Hello world!";
  }

  @GetMapping("/withPath")
  public String testControllerWithPath() {
    return "Hello spring!";
  }

  @GetMapping("/{id}")
  public String testControllerPathVar(@PathVariable(required = false) int id) {
    return "Hello World! ID : " + id;
  }

  @GetMapping("/param")
  public String testControllerParam(@RequestParam(required = false) int id) {
    return "Hello World! param ID : " + id;
  }

  @GetMapping("/testRequestBody")
  public String testControllerRequstBody(@RequestBody TestRequestBodyDTO dto) {
    return "Hello World! id : " + dto.getId() + " msg : " + dto.getMessage();
  }
}
