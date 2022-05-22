package com.godseun.todolistspring.service;

import com.godseun.todolistspring.model.UserEntity;
import com.godseun.todolistspring.persistence.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public UserEntity create(final UserEntity userEntity) {
    if (null == userEntity || null == userEntity.getEmail()) {
      throw new RuntimeException("Invalid arguments");
    }

    final String email = userEntity.getEmail();
    if (userRepository.existsByEmail(email)) {
      log.warn("Email already exists {}", email);
      throw new RuntimeException("Email already exists");
    }

    return userRepository.save(userEntity);
  }

  public UserEntity getByCredentials(final String email, final String password) {
    return userRepository.findByEmailAndPassword(email, password);
  }
}
