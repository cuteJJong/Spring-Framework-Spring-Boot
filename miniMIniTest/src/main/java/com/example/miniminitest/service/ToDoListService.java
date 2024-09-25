// ToDoListService.java
package com.example.miniminitest.service;

import com.example.miniminitest.dto.TodoListDto;
import com.example.miniminitest.entity.ToDoList;
import com.example.miniminitest.entity.User;
import com.example.miniminitest.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ToDoListService {
    @Autowired
    private ToDoListRepository repository;

    // 할 일 추가
    public TodoListDto saveList(TodoListDto dto, User user) {
        ToDoList entity = repository.save(new ToDoList(dto.getTitle(), dto.getContent(), user));
        return new TodoListDto(entity.getNum(), entity.getTitle(), entity.getContent());
    }

    // 모든 할 일 조회
    public ArrayList<TodoListDto> getAll(User user) {
        ArrayList<ToDoList> l = (ArrayList<ToDoList>) repository.findByUser(user);
        ArrayList<TodoListDto> list = new ArrayList<>();
        for (ToDoList entity : l) {
            list.add(new TodoListDto(entity.getNum(), entity.getTitle(), entity.getContent()));
        }
        return list;
    }

    // 수정
    public TodoListDto updateList(int num, TodoListDto dto, User user) {
        ToDoList entity = repository.findByNum(num);
        if (entity != null && entity.getUser().getId().equals(user.getId())) {
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());

            ToDoList updatedEntity = repository.save(entity);
            return new TodoListDto(updatedEntity.getNum(), updatedEntity.getTitle(), updatedEntity.getContent());
        }
        return null;
    }

    // 삭제
    public void delToDoList(int num, User user) {
        ToDoList entity = repository.findByNum(num);
        if (entity != null && entity.getUser().getId().equals(user.getId())) {
            repository.delete(entity);
        }
    }

    // 번호로 조회
    public TodoListDto getByNum(int num, User user) {
        ToDoList entity = repository.findByNum(num);
        if (entity != null && entity.getUser().getId().equals(user.getId())) {
            return new TodoListDto(entity.getNum(), entity.getTitle(), entity.getContent());
        }
        throw new RuntimeException("Item not found or unauthorized");
    }

    // 제목으로 검색
    public ArrayList<TodoListDto> getByTitle(String title, User user) {
        ArrayList<ToDoList> entities = repository.findByTitleContainingAndUser(title, user);

        // 검색 결과가 없으면 빈 리스트 반환
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<TodoListDto> list = new ArrayList<>();
        for (ToDoList entity : entities) {
            list.add(new TodoListDto(entity.getNum(), entity.getTitle(), entity.getContent()));
        }
        return list;
    }
}
