package com.example.demo.board;

import lombok.*;

import java.sql.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
	private int num;
	private String writer;
	private Date wdate;
	private String title;
	private String content;
}
