package com.godseun.todolistspring.controller;

import com.godseun.todolistspring.dto.ResponseDTO;
import com.godseun.todolistspring.dto.UserDTO;
import com.godseun.todolistspring.model.UserEntity;
import com.godseun.todolistspring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
    try {
      UserEntity user = UserEntity.builder()
          .email(userDTO.getEmail())
          .userName(userDTO.getUserName())
          .password(userDTO.getPassword()).build();

      UserEntity registeredUser = userService.create(user);
      UserDTO responseUserDTO = UserDTO.builder()
          .email(registeredUser.getEmail())
          .id(registeredUser.getId())
          .userName(registeredUser.getUserName()).build();

      return ResponseEntity.ok().body(responseUserDTO);
    } catch (Exception e) {
      ResponseDTO<UserDTO> responseDTO = ResponseDTO.<UserDTO>builder().error(e.getMessage()).build();
      return ResponseEntity.badRequest().body(responseDTO);
    }
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
    UserEntity user = userService.getByCredentials(
        userDTO.getEmail(),
        userDTO.getPassword());

    if (null != user) {
      final UserDTO responseUserDTO = UserDTO.builder()
          .email(user.getEmail())
          .id(user.getId()).build();

      return ResponseEntity.ok().body(responseUserDTO);
    } else {
      ResponseDTO<UserDTO> responseDTO = ResponseDTO.<UserDTO>builder().error("Login failed.").build();
      return ResponseEntity.badRequest().body(responseDTO);
    }
  }
}
