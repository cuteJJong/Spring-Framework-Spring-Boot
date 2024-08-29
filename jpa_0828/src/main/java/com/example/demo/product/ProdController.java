package com.example.demo.product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/prod")
public class ProdController {
	@Autowired
	private ProductService service;

	@Value("${spring.servlet.multipart.location}")
	private String path; // path = C:\shopimg

	@GetMapping("/add")
	public void addForm() {
	}

	@PostMapping("/add")
	public String add(ProductDto dto) {
		//num, name, price, amount
		ProductDto newData = service.saveProd(dto);//insert
		String fname = dto.getF().getOriginalFilename();
		fname = newData.getNum() + fname;
		File newf = new File(path + "\\" + fname);
		try {
			dto.getF().transferTo(newf);// 업로드 파일을 newf에 복사
			newData.setImg(fname);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.saveProd(newData);//update
		return "redirect:/prod/list";
	}

	@RequestMapping("/list")
	public void list(Model m, HttpSession session) {
		String type = (String) session.getAttribute("type");
		String loginId = (String) session.getAttribute("loginId");
		ArrayList<ProductDto> list = null;
		if (type.equals("판매자")) {
			list = service.getBySeller(loginId);
		} else {
			list = service.getAll();
		}
		m.addAttribute("list", list);
	}

	@GetMapping("/read-img")
	public ResponseEntity<byte[]> read_img(String fname) {
		ResponseEntity<byte[]> result = null;
		File f = new File(path + "\\" + fname);
		// 응답 헤더 정보 저장 객체
		HttpHeaders header = new HttpHeaders();
		try {
			// 전송하는 데이터의 마임 타입 설정
			header.add("Content-Type", Files.probeContentType(f.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(f), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
