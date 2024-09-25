// UserService.java
package com.example.miniminitest.service;

import com.example.miniminitest.dto.UserDto;
import com.example.miniminitest.entity.User;
import com.example.miniminitest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    // 회원가입
    public UserDto saveMem(UserDto dto) {
        // 비밀번호 해싱
        User newUser = new User(dto.getLoginId(), "", dto.getName());
        newUser.setPassword(dto.getPwd());

        User entity = repository.save(newUser);
        return new UserDto(entity.getId(), entity.getLoginId(), entity.getPwd(), entity.getName());
    }

    // 로그인 검증
    public UserDto validateUser(String loginId, String password) {
        User user = repository.findByLoginId(loginId);
        if (user != null && user.checkPassword(password)) {
            return new UserDto(user.getId(), user.getLoginId(), user.getPwd(), user.getName());
        }
        return null;
    }

    // 로그인 시 사용자 조회
    public User findByLoginId(String loginId) {
        return repository.findByLoginId(loginId);
    }

    // 기타 메소드 (수정, 조회, 삭제) 생략
}
