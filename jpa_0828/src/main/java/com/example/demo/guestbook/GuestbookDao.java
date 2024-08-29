package com.example.demo.guestbook;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestbookDao extends JpaRepository<Guestbook, Integer> {
	ArrayList<Guestbook> findByWriter(String writer);
}
