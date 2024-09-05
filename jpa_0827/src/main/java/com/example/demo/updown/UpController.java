package com.example.demo.updown;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UpController {
	
	@GetMapping("/form")
	public void uploadForm() {
	}

	@PostMapping("/upload")
	public String upload(MultipartFile f, String title) {
		//MultipartFile: 파일 업로드 api
		//getOriginalFilename(): 원본 파일명 반환
		String fname = f.getOriginalFilename();
		File newf = new File("C:\\img\\" + fname);
		try {
			f.transferTo(newf);//업로드 파일을 newf에 복사
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fname:" + fname);
		System.out.println("title:" + title);
		return "redirect:/upload/list";
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		//C:\\img\\ 폴더의 파일 목록을 읽어서 m에 담아서 list.html 파일명 목록 출력
		File dir = new File("C:\\img\\");
		String[] files = dir.list();//파일 목록
		m.addAttribute("files", files);
	}
	
	@GetMapping("/read-img")
	public ResponseEntity<byte[]> read_img(String fname){
		ResponseEntity<byte[]> result = null;
		File f = new File("C:\\img\\"+fname);
		//응답 헤더 정보 저장 객체
		HttpHeaders header = new HttpHeaders();
		try {
			//전송하는 데이터의 마임 타입 설정
			header.add("Content-Type", Files.probeContentType(f.toPath()));
			result = new ResponseEntity<byte[]>(
						FileCopyUtils.copyToByteArray(f), header, HttpStatus.OK
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}




