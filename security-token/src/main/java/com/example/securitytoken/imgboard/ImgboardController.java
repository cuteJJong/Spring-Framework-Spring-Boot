package com.example.securitytoken.imgboard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import com.example.securitytoken.users.Users;

@RestController
@CrossOrigin(origins = "*")
public class ImgboardController {
	@Autowired
	private ImgboardService service;
	
	@Value("${spring.servlet.multipart.location}")
	private String path;
	
	@PostMapping("/board")
	public Map add(ImgboardDto dto) {
		String fname = dto.getF().getOriginalFilename();
		File newf = new File(path + fname);
		boolean flag = false;
		try {
			dto.getF().transferTo(newf);
			dto.setImg(fname);
			Authentication a = 
					SecurityContextHolder.getContext().getAuthentication();
			Users u = new Users(a.getName(), "", "");
			dto.setWriter(u);
			service.save(dto);
			flag = true;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map map = new HashMap();
		map.put("flag", flag);
		return map;
	}

	@GetMapping("/board")
	public Map list() {
		Map map = new HashMap<>();
		map.put("list", service.getAll());
		return map;
	}

	@GetMapping("/read-img")
	public ResponseEntity<byte[]> read_img(@PathVariable("fname") String fname) {
		ResponseEntity<byte[]> result = null;
		File f = new File(path + fname);
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
