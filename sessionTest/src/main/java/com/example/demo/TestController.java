package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class TestController {
	
	@GetMapping("/test/login")
	public void loginForm() {}
	
	@PostMapping("/test/login")
	public String login(String id, String pwd, HttpSession session) {
		//세션 정보 확인
		System.out.println("session id:" + session.getId());
		System.out.println("CreationTime:" + session.getCreationTime());
		System.out.println("sessionLastAccessedTime:" + session.getLastAccessedTime());
		System.out.println("sessionMaxInactiveInterval:" + session.getMaxInactiveInterval());
		
		//로그인 처리
		//aaa, 111
		String msg = "로그인 실패";
		if(id.equals("aaa") && pwd.equals("111")) {
			session.setAttribute("loginId", "aaa");
			session.setAttribute("type", "구매자");
			msg = "로그인 성공";
		}
		
		session.setAttribute("msg", msg);
		return "index";
	}
	
	@GetMapping("/test/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
}
