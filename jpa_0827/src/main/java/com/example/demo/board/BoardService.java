package com.example.demo.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;
	
	//추가, 수정
	public void saveBoard(BoardDto b) {
		dao.save(new Board(b.getNum(), b.getWriter(), b.getWdate(), b.getTitle(), b.getContent()));
	}
	
	public BoardDto getBoard(int num) {
		Board b = dao.findById(num).orElse(null);
		if(b != null) {
			return new BoardDto(b.getNum(), b.getWriter(), b.getWdate(), 
					b.getTitle(), b.getContent());
		}
		return null;
	}
	
	public ArrayList<BoardDto> getAll(){
		List<Board> l = dao.findAll();
		ArrayList<BoardDto> list = new ArrayList<>();
		for(Board b:l) {
			list.add(new BoardDto(b.getNum(), b.getWriter(), b.getWdate(), 
					b.getTitle(), b.getContent()));
		}
		return list;
	}
	
	public ArrayList<BoardDto> getByTitle(String title){
		List<Board> l = dao.findByTitleLike("%"+title+"%");
		ArrayList<BoardDto> list = new ArrayList<>();
		for(Board b:l) {
			list.add(new BoardDto(b.getNum(), b.getWriter(), b.getWdate(), 
					b.getTitle(), b.getContent()));
		}
		return list;
	}
	
	public ArrayList<BoardDto> getByWriter(String writer){
		List<Board> l = dao.findByWriter(new Member(writer, "","","",""));
		ArrayList<BoardDto> list = new ArrayList<>();
		for(Board b:l) {
			list.add(new BoardDto(b.getNum(), b.getWriter(), b.getWdate(), 
					b.getTitle(), b.getContent()));
		}
		return list;
	}
	
	public void delBoard(int num) {
		dao.deleteById(num);
	}
}
