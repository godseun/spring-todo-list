package com.godseun.todolistspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

  private String token;
  private String email;
  private String userName;
  private String password;
  private String id;
}
