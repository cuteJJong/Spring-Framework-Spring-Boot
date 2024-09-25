// ToDoListController.java
package com.example.miniminitest.controller;

import com.example.miniminitest.dto.TodoListDto;
import com.example.miniminitest.entity.User;
import com.example.miniminitest.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/todo")
@CrossOrigin(origins = "http://localhost:5173")
public class ToDoListController {

    @Autowired
    private ToDoListService service;

    // 추가
    @PostMapping("/save")
    public ResponseEntity<?> saveToDo(@RequestBody TodoListDto dto, @RequestAttribute("user") User user) {
        TodoListDto savedDto = service.saveList(dto, user);
        return ResponseEntity.ok(savedDto);
    }

    // 조회
    @GetMapping("/all")
    public ResponseEntity<?> getAllToDos(@RequestAttribute("user") User user) {
        ArrayList<TodoListDto> list = service.getAll(user);
        return ResponseEntity.ok(list);
    }

    // 세부 조회 (번호로)
    @GetMapping("/search")
    public ResponseEntity<?> getToDoByNum(@RequestParam int num, @RequestAttribute("user") User user) {
        TodoListDto dto = service.getByNum(num, user);
        return ResponseEntity.ok(dto);
    }

    // 제목으로 검색
    @GetMapping("/searchByTitle")
    public ResponseEntity<?> getToDoByTitle(@RequestParam String title, @RequestAttribute("user") User user) {
        ArrayList<TodoListDto> list = service.getByTitle(title, user);
        return ResponseEntity.ok(list);
    }

    // 수정
    @PutMapping("/update/{num}")
    public ResponseEntity<?> updateToDo(@PathVariable int num, @RequestBody TodoListDto dto, @RequestAttribute("user") User user) {
        TodoListDto updatedDto = service.updateList(num, dto, user);
        if (updatedDto != null) {
            return ResponseEntity.ok(updatedDto);
        }
        return ResponseEntity.status(404).body("할 일을 찾을 수 없거나 권한이 없습니다.");
    }

    // 삭제
    @DeleteMapping("/delete/{num}")
    public ResponseEntity<?> deleteToDo(@PathVariable int num, @RequestAttribute("user") User user) {
        service.delToDoList(num, user);
        return ResponseEntity.ok("삭제 성공");
    }
}
