package com.example.miniminitest.repository;

import com.example.miniminitest.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    ToDoList findByNum(int num);

    // 제목으로 검색 추가
    ArrayList<ToDoList> findByTitleContaining(String title);
}
