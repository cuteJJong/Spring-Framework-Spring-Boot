// UserRepository.java
package com.example.miniminitest.repository;

import com.example.miniminitest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByLoginId(String loginId);
}
