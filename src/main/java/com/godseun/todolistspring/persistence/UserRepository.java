package com.godseun.todolistspring.persistence;

import com.godseun.todolistspring.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

  UserEntity findByEmail(String email);

  Boolean existsByEmail(String email);

  UserEntity findByEmailAndPassword(String email, String password);
}
