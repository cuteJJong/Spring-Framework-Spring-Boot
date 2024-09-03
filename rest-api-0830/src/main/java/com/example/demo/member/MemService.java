package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemService {
	@Autowired
	private MemDao dao;

	//추가, 수정:모든 컬럼 수정
	public MemberDto saveMem(MemberDto m) {
		Member entity = dao.save(new Member(m.getId(), m.getPwd(), 
				m.getName(), m.getEmail(), m.getType()));
		return new MemberDto(entity.getId(), entity.getPwd(), 
				entity.getName(), entity.getEmail(), entity.getType());
	}

	public MemberDto getMem(String id) {
		Member entity = dao.findById(id).orElse(null);
		if(entity != null) {
			return new MemberDto(entity.getId(), entity.getPwd(), entity.getName(), 
					entity.getEmail(), entity.getType());
		}
		return null;
	}

	public void delMem(String id) {
		dao.deleteById(id);
	}	
}
