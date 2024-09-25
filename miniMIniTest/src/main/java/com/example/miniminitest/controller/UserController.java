//package com.example.miniminitest.controller;
//
//import com.example.miniminitest.dto.UserDto;
//import com.example.miniminitest.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.lang.reflect.Member;
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//    @Autowired
//    private UserService service;
//
//
//    //조회
//    @GetMapping("/all")
//    public ArrayList<UserDto> getAllMem() {
//       return service.getAll();
//    }
//
//    //회원가입
//    @PostMapping("/register")
//    public UserDto registerMem(@RequestBody UserDto dto) {
//        System.out.println("Received: " + dto.getLoginId() + ", " + dto.getPwd() + ", " + dto.getName());
//        return service.saveMem(dto);
//    }
//
//    //로그인
//    @PostMapping("/login")
//    public String loginUser(@RequestBody UserDto dto) {
//        boolean isValidUser = service.validateUser(dto.getLoginId(), dto.getPwd());
//        if (isValidUser) {
//            return "로그인 성공";
//        } else {
//            return "로그인 실패";
//        }
//    }
//
//    //추가
//    @PostMapping("/save")
//    public UserDto saveMem(@RequestBody UserDto dto) {
//        return service.saveMem(dto);
//    }
//
//    //수정
//    @PostMapping("/update/{id}")
//    public UserDto updateMem(@PathVariable int id, @RequestBody UserDto dto) {
//        return service.updateMem(id, dto);
//    }
//
//    //삭제
//    @DeleteMapping("/delete/{id}")
//    public String deleteMem(@PathVariable int id) {
//        service.DeleteMem(id);
//        return "삭제 성공";
//    }
//}
