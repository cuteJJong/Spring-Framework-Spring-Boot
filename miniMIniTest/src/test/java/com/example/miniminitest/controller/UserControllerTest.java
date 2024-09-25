package com.example.miniminitest.controller;

import com.example.miniminitest.dto.UserDto;
import com.example.miniminitest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllUsers() throws Exception {
        // given
        ArrayList<UserDto> list = new ArrayList<>();
        list.add(new UserDto(1, "user1", "password1", "name1"));
        list.add(new UserDto(2, "user2", "password2", "name2"));

        // when
        Mockito.when(service.getAll()).thenReturn(list);

        // then
        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].loginId").value("user1"))
                .andExpect(jsonPath("$[0].name").value("name1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].loginId").value("user2"))
                .andExpect(jsonPath("$[1].name").value("name2"));
    }

    @Test
    void testSaveUser() throws Exception {
        // given
        UserDto dto = new UserDto(2, "user3", "password3", "name1");

        // when
        Mockito.when(service.saveMem(any(UserDto.class))).thenReturn(dto);

        // then
        mockMvc.perform(post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.loginId").value("user3"))
                .andExpect(jsonPath("$.pwd").value("password3"))
                .andExpect(jsonPath("$.name").value("name1"));
    }

    @Test
    void testUpdateUser() throws Exception {
        // given:
        UserDto updatedDto = new UserDto(1, "user1_updated", "password_updated", "name_updated");

        // when:
        Mockito.when(service.updateMem(Mockito.eq(1), any(UserDto.class))).thenReturn(updatedDto);

        // then:
        mockMvc.perform(post("/api/user/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.loginId").value("user1_updated"))
                .andExpect(jsonPath("$.pwd").value("password_updated"))
                .andExpect(jsonPath("$.name").value("name_updated"));
    }

    @Test
    void testDeleteUser() throws Exception {
        // when
        Mockito.doNothing().when(service).DeleteMem(1);

        // then
        mockMvc.perform(delete("/api/user/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("삭제 성공"));
    }

    @Test
    void testRegisterUser() throws Exception {
        // given
        UserDto dto = new UserDto(1, "user1", "password1", "name");

        // when
        Mockito.when(service.saveMem(any(UserDto.class))).thenReturn(dto);

        // then
        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.loginId").value("user1"))
                .andExpect(jsonPath("$.pwd").value("password1"));
                //.andExpect(jsonPath("$.name").value("name1"));
    }

    @Test
    void testLoginUser_Success() throws Exception {
        // given
        UserDto dto = new UserDto(1, "user1", "password1", "name1");

        // when
        Mockito.when(service.validateUser("user1", "password1")).thenReturn(true);

        // then
        mockMvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("로그인 성공"));
    }

    @Test
    void testLoginUser_Failure() throws Exception {
        // given
        UserDto dto = new UserDto(1, "user1", "wrongpassword", "name");

        // when
        Mockito.when(service.validateUser("user1", "wrongpassword")).thenReturn(false);

        // then
        mockMvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("로그인 실패"));
    }
}
