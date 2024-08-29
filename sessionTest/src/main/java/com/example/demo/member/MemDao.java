package com.example.demo.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemDao {
	@Autowired
	private JdbcTemplate temp;
	
	public class MemberResultMap implements RowMapper<Member> {
	
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5));
					
		}
	}
	
	public void insert(Member m) {
		String sql = "insert into member values(?,?,?,?,?)";
		temp.update(sql, new Object[]
				{m.getId(),m.getPwd(),m.getName(),m.getEmail(),m.getType()});
	}
	
	public Member select(String id) {
		String sql = "select * from member where id=?";
		Member m = null;
		try {
			m = temp.queryForObject(sql, new MemberResultMap(), id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return m;
	}
	
	public void update(Member m ) {
		String sql = "update member set name=?, email=?, type=? where id =?";
		temp.update(sql, new Object[]
				{m.getId(),m.getPwd(),m.getName(),m.getEmail(),m.getType()});
	}
	
	public void delete(String id) {
		String sql = "delete from member where id=?";
		temp.update(sql, id);
	}
}
