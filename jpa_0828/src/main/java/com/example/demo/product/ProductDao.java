package com.example.demo.product;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.member.Member;
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	ArrayList<Product> findBySeller(Member seller);
	ArrayList<Product> findByNameLike(String name);
	ArrayList<Product> findByPriceBetween(int p1, int p2);
}
