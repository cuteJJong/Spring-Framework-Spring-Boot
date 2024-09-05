package com.example.demo.down;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/down")
public class DownController {

	private String path = "C:\\down\\";

	@GetMapping("")
	public String flist(ModelMap map) {// 파일 목록을 읽어서 출력
		File dir = new File(path);
		map.addAttribute("list", dir.list());
		return "down/list";
	}

	@GetMapping("/down")
	public ResponseEntity<byte[]> read_img(String fname) {
		ResponseEntity<byte[]> result = null;
		File f = new File(path + fname);
		HttpHeaders header = new HttpHeaders(); // import org.springframework.http.HttpHeaders;
		try {
			header.add("Content-Type", Files.probeContentType(f.toPath()));
			header.add(HttpHeaders.CONTENT_DISPOSITION,
					"attachment;filename=\"" +
			URLEncoder.encode(fname, "UTF-8") + "\"");
			result = new ResponseEntity<byte[]>(
					FileCopyUtils.copyToByteArray(f), header, 
					HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
