package com.example.miniminitest.service;

import com.example.miniminitest.dto.TodoListDto;
import com.example.miniminitest.entity.ToDoList;
import com.example.miniminitest.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ToDoListService {
    @Autowired
    private ToDoListRepository repository;

    // 추가
    public TodoListDto saveList(TodoListDto dto) {
        ToDoList entity = repository.save(new ToDoList(
                dto.getTitle(), dto.getContent()));

        return new TodoListDto(entity.getNum(), entity.getTitle(),
                entity.getContent());
    }

    // 조회
    public ArrayList<TodoListDto> getAll() {
        ArrayList<ToDoList> l = (ArrayList<ToDoList>) repository.findAll();
        ArrayList<TodoListDto> list = new ArrayList<>();
        for (ToDoList entity : l) {
            list.add(new TodoListDto(entity.getNum(), entity.getTitle(),
                    entity.getContent()));
        }
        return list;
    }

    // 수정
    public TodoListDto updateList(int num, TodoListDto dto) {
        ToDoList entity = repository.findById(num).orElse(null);
        if (entity != null) {
            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());

            ToDoList updatedEntity = repository.save(entity);
            return new TodoListDto(updatedEntity.getNum(), updatedEntity.getTitle(), updatedEntity.getContent());
        }
        return null;
    }

    // 삭제
    public void delToDoList(int num) {
        repository.deleteById(num);
    }

    // 번호로 조회
    public TodoListDto getByNum(int num) {
        ToDoList entity = repository.findById(num).orElseThrow(() -> new RuntimeException("Item not found"));
        return new TodoListDto(entity.getNum(), entity.getTitle(), entity.getContent());
    }

    // 제목으로 검색
    public ArrayList<TodoListDto> getByTitle(String title) {
        ArrayList<ToDoList> entities = repository.findByTitleContaining(title);

        // 검색 결과가 없으면 전체 목록 반환
        if (entities.isEmpty()) {
            entities = (ArrayList<ToDoList>) repository.findAll();
        }

        ArrayList<TodoListDto> list = new ArrayList<>();
        for (ToDoList entity : entities) {
            list.add(new TodoListDto(entity.getNum(), entity.getTitle(), entity.getContent()));
        }
        return list;
    }

}
