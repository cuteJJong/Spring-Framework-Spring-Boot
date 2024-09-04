package com.example.securitytoken.imgboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgboardService {
	@Autowired
	private ImgboardDao dao;
	
	public void save(ImgboardDto dto) {
		dao.save(new Imgboard(dto.getNum(), dto.getWriter(), 
				dto.getTitle(), dto.getImg()));
	}
	
	public ArrayList<ImgboardDto> getAll(){
		List<Imgboard> l = dao.findAll();
		ArrayList<ImgboardDto> list = new ArrayList<>();
		for(Imgboard entity:l) {
			list.add(new ImgboardDto(entity.getNum(), entity.getWriter(), 
				entity.getTitle(), entity.getImg(), null));
		}
		return list;
	}
}
