package com.example.market.controller;

import com.example.market.model.User;
import com.example.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return userService.login(user);
    }
}
