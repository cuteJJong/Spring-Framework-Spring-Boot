package com.example.security.auth;

import com.example.security.users.Users;
import com.example.security.users.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersDao dao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = dao.findById(username).orElseThrow(()->new UsernameNotFoundException("not found username"+username));
        System.out.println("userdetails service = " + u);
        return new UserDetailsImpl(u);
    }

}
