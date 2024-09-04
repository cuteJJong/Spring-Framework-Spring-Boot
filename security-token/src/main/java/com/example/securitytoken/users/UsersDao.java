package com.example.securitytoken.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsersDao extends JpaRepository<Users, String> {

}
