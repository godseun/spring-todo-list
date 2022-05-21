package com.godseun.todolistspring.persistence;

import java.util.List;

import com.godseun.todolistspring.model.TodoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

  // @Query("select * from Todo t where t.userId = ?1")
  // @Query는 더 복잡한 쿼리에 사용됨, ?1 은 매개변수 순서
  List<TodoEntity> findByUserId(String userId); // 매개변수는 where절에 들어감
}
