package com.example.securitytoken.imgboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ImgboardDao extends JpaRepository<Imgboard, Integer> {

}
