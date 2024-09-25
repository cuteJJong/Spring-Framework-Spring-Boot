// AuthController.java
package com.example.miniminitest.controller;

import com.example.miniminitest.dto.UserDto;
import com.example.miniminitest.entity.User;
import com.example.miniminitest.service.UserService;
import com.example.miniminitest.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173") // 프론트엔드 주소에 맞게 수정
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto dto) {
        // 로그인 ID 중복 확인
        if (userService.findByLoginId(dto.getLoginId()) != null) {
            return ResponseEntity.badRequest().body("이미 사용 중인 아이디입니다.");
        }

        UserDto savedUser = userService.saveMem(dto);
        return ResponseEntity.ok(savedUser);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto dto) {
        UserDto user = userService.validateUser(dto.getLoginId(), dto.getPwd());
        if (user != null) {
            String token = jwtUtil.generateToken(user.getLoginId());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).body("아이디 또는 비밀번호가 올바르지 않습니다.");
    }

    // 인증 응답 DTO
    @Data
    @AllArgsConstructor
    public static class AuthResponse {
        private String token;
    }
}
