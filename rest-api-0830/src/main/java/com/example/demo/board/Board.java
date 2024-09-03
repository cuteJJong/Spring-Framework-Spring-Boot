package com.example.demo.board;


import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
	@Id   //pk 정의
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동 넘버링. auto_increment 설정
	private int num;
	
	@ManyToOne  //board:member => 다대일
	@JoinColumn(nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE) //on delete cascade 설정
	private Member writer;//연관되는 entity 타입으로 지정
	private Date wdate;
	private String title;
	private String content;
	
	@PrePersist //insert 전 자동호출
	public void makeDate() {
		wdate = new Date();
	}
}
