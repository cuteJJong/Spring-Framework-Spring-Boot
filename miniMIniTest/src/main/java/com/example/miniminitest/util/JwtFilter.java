// JwtFilter.java
package com.example.miniminitest.util;

import com.example.miniminitest.entity.User;
import com.example.miniminitest.repository.UserRepository;
import com.example.miniminitest.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        String token = null;
        String loginId = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                loginId = jwtUtil.extractLoginId(token);
            }
        }

        if (loginId != null && request.getAttribute("user") == null) {
            User user = userRepository.findByLoginId(loginId);
            if (user != null) {
                request.setAttribute("user", user);
            }
        }

        filterChain.doFilter(request, response);
    }
}
