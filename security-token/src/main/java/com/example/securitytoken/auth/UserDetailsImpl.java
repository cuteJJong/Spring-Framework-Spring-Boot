package com.example.securitytoken.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.securitytoken.users.Users;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserDetailsImpl implements UserDetails {

	private final Users u;
	
	public UserDetailsImpl(Users u) {
		this.u = u;
	}

	//user의 권한 정보 설정
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(u.getType()));
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return u.getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return u.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
