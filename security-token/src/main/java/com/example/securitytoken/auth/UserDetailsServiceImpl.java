package com.example.securitytoken.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securitytoken.users.Users;
import com.example.securitytoken.users.UsersDao;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UsersDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users u = dao.findById(username)
		.orElseThrow(
		()->new UsernameNotFoundException("not found username"+username));
		System.out.println("userdetails service:"+u);
		return new UserDetailsImpl(u);
	}

}
