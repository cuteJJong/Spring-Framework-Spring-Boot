package com.example.demo.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemController {
	@Autowired
	public MemService service;

	@GetMapping("/join")
	public String joinForm() {
		return "member/joinAjax";
	}

	@PostMapping("/join")
	public String join(Member m) {
		service.addMem(m);
		return "index";
	}

	@PostMapping("/idcheck")
	public String idcheck(String id, Model model) {
		Member m = service.getMem(id);
		String msg = "중복된 아이디";
		String resultid = "";
		boolean flag = false;
		if(m==null) {
			msg = "사용가능한 아이디";
			resultid = id;
			flag = true;
		}
		model.addAttribute("resultid", resultid);
		model.addAttribute("msg", msg);
		model.addAttribute("flag", flag);
		return "member/idcheck";
	}

	@ResponseBody
	@GetMapping("/idcheckAjax")
	public Map idcheckAjax(String id) {
		Map map = new HashMap();
		boolean flag = false;
		if(service.getMem(id)  == null) {
			flag = true;
		}
		map.put("flag", flag);
		return map;
	}

	@GetMapping("/login")
	public void loginForm() {}

	@PostMapping("/login")
	public String login(Member m, HttpSession session, Model model) {
		Member m2 = service.getMem(m.getId());
		if(m2 != null && m2.getPwd().equals(m.getPwd())) {
			session.setAttribute("loginId", m2.getId());
			session.setAttribute("type", m2.getType());
		} else {
			model.addAttribute("msg", "로그인 실패");
		}
		return "index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@GetMapping("/myinfo")
	public void myinfo(HttpSession session, Model m) {
		m.addAttribute("m", service.getMem((String)session.getAttribute("loginId")));
	}

	@PostMapping("/edit")
	public String edit(Member m, HttpSession session ) {
		service.editMem(m);
		Member m2 = service.getMem(m.getId());
		session.setAttribute("type", m2.getType());
		return "redirect:/index.jsp";
	}

	//forward : 서버 내에서 이동. url 안바뀜. / member / join => index
	//redirect : 새 요청 시킴. 새 req. / member / join => index
	@GetMapping("/out")
	public String out(HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		service.delMem(loginId);
		return "redirect:/member/logout";
	}
}
