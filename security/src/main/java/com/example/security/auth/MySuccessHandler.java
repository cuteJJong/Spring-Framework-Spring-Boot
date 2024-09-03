package com.example.security.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

//인증 성공 시 실행
public class MySuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String loginId = authentication.getName();  //로그인 아이디
        System.out.println("로그인 성공 / loginId = " + loginId);

        String path = "";
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("구매자"))){
            System.out.println("구매자");
            path = "/index_con";
        }
        else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("판매자"))) {
            System.out.println("판매자");
            path = "/index_sel";
        }
        else {
            System.out.println("권한 모름");
            path = "/";
        }
        //path 경로로 forward로 이동
        request.getRequestDispatcher(path).forward(request, response);
    }
}
