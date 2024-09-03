package com.example.security.auth;

import com.example.security.users.Users;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class UserDetailsImpl implements UserDetails {

    private final Users u;

    public UserDetailsImpl(Users u ) {
        this.u = u;
    }

    //user의 권한 정보 설정
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(u.getType()));
        return list;
    }

    @Override
    public String getPassword() {
        return u.getPwd();
    }

    @Override
    public String getUsername() {
        return u.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
