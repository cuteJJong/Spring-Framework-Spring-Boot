// SecurityConfig.java
package com.example.miniminitest;

import com.example.miniminitest.util.JwtFilter;
import com.example.miniminitest.util.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 비활성화 (API용)
                .csrf(csrf -> csrf.disable())

                // 세션 관리 비활성화 (JWT 사용 시)
                .sessionManagement(session -> session.disable())

                // 요청 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        // 로그인 및 회원가입 엔드포인트는 인증 없이 접근 허용
                        .requestMatchers("/api/user/login", "/api/user/register").permitAll()
                        // 기타 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                );

        // JWT 필터를 UsernamePasswordAuthenticationFilter 전에 추가
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // AuthenticationManager 빈 정의
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
