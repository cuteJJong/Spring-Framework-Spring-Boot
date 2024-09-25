// User.java
package com.example.miniminitest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String name;

    public User(String loginId, String pwd, String name) {
        this.loginId = loginId;
        this.pwd = pwd;
        this.name = name;
    }

    // 비밀번호 해싱 메소드
    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.pwd = passwordEncoder.encode(password);
    }

    // 비밀번호 검증 메소드
    public boolean checkPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, this.pwd);
    }
}
