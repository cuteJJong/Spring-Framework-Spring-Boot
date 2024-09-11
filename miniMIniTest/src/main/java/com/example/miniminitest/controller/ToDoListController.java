package com.example.miniminitest.controller;

import com.example.miniminitest.dto.TodoListDto;
import com.example.miniminitest.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public TodoListDto saveToDo(@RequestBody TodoListDto dto) {
        return service.saveList(dto);
    }

    // 조회
    @GetMapping("/all")
    public ArrayList<TodoListDto> getAllToDos() {
        return service.getAll();
    }

    // 세부 조회 (번호로)
    @GetMapping("/search")
    public TodoListDto getToDoByNum(@RequestParam int num) {
        return service.getByNum(num);
    }

    // 제목으로 검색
    @GetMapping("/searchByTitle")
    public ArrayList<TodoListDto> getToDoByTitle(@RequestParam String title) {
        return service.getByTitle(title);
    }

    // 수정
    @PutMapping("/update/{num}")
    public TodoListDto updateToDo(@PathVariable int num, @RequestBody TodoListDto dto) {
        return service.updateList(num, dto);
    }

    // 삭제
    @DeleteMapping("/delete/{num}")
    public String deleteToDo(@PathVariable int num) {
        service.delToDoList(num);
        return "삭제 성공";
    }
}
