package com.example.securitytoken.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
	@Autowired
	private UsersDao dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	//추가, 수정
	public void save(UsersDto dto) {
		dao.save(new Users(dto.getId(), encoder.encode(dto.getPwd()),
				dto.getType()));
	}
	
	//pk 검색
	public UsersDto get(String id) {
		Users entity = dao.findById(id).orElse(null);
		if(entity != null) {
			return new UsersDto(entity.getId(),entity.getPwd(), 
					entity.getType());
		}
		return null;
	}
	
	//삭제
	public void del(String id) {
		dao.deleteById(id);
	}
}




