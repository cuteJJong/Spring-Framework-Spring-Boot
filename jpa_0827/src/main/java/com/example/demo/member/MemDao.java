package com.example.demo.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MemDao extends JpaRepository<Member, String> {

}
