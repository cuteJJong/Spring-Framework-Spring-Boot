// ToDoListRepository.java
package com.example.miniminitest.repository;

import com.example.miniminitest.entity.ToDoList;
import com.example.miniminitest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    ToDoList findByNum(int num);

    // 제목으로 검색 및 사용자 필터링
    ArrayList<ToDoList> findByTitleContainingAndUser(String title, User user);

    // 사용자 별로 모든 할 일 조회
    ArrayList<ToDoList> findByUser(User user);
}
