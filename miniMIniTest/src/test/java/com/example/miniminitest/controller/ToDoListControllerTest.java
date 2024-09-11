package com.example.miniminitest.controller;

import com.example.miniminitest.dto.TodoListDto;
import com.example.miniminitest.service.ToDoListService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoListService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveToDo() throws Exception {
        //given
        TodoListDto dto = new TodoListDto(1, "Test Title", "Test Content");

        //when
        Mockito.when(service.saveList(any(TodoListDto.class))).thenReturn(dto);

        //then
        mockMvc.perform(post("/api/todo/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.num").value(1))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"));
    }

    @Test
    void testGetAllToDos() throws Exception {
        ArrayList<TodoListDto> list = new ArrayList<>();
        //given
        list.add(new TodoListDto(1, "Title 1", "Content 1"));
        list.add(new TodoListDto(2, "Title 2", "Content 2"));

        //when
        Mockito.when(service.getAll()).thenReturn(list);

        //then
        mockMvc.perform(get("/api/todo/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].num").value(1))
                .andExpect(jsonPath("$[0].title").value("Title 1"))
                .andExpect(jsonPath("$[1].num").value(2))
                .andExpect(jsonPath("$[1].title").value("Title 2"));
    }

    @Test
    void testGetToDoByNum() throws Exception {
        //given
        TodoListDto dto = new TodoListDto(1, "Test Title", "Test Content");

        //when
        Mockito.when(service.getByNum(1)).thenReturn(dto);

        //then
        mockMvc.perform(get("/api/todo/search?num=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.num").value(1))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"));
    }

    @Test
    void testUpdateToDo() throws Exception {
        //given
        TodoListDto updatedDto = new TodoListDto(1, "Updated Title", "Updated Content");

        //when
        Mockito.when(service.updateList(Mockito.eq(1), any(TodoListDto.class))).thenReturn(updatedDto);

        //then
        mockMvc.perform(put("/api/todo/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.num").value(1))
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.content").value("Updated Content"));
    }

    @Test
    void testDeleteToDo() throws Exception {
        //when
        Mockito.doNothing().when(service).delToDoList(1);

        //then
        mockMvc.perform(delete("/api/todo/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("삭제 성공"));
    }
}
