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
import org.springframework.web.multipart.MultipartFile;

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
	public String add(ProductDto dto) {// dto:name, price, amount, f, seller
		// num, name, price, amount, seller
		ProductDto newData = service.saveProd(dto);// insert
		String fname = dto.getF().getOriginalFilename();
		fname = newData.getNum() + fname;
		File newf = new File(path + fname);
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
		service.saveProd(newData);// update
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

	@GetMapping("/detail")
	public void detail(int num, Model m) {
		m.addAttribute("prod", service.getProd(num));
	}
	
	@PostMapping("/edit")
	public String edit(ProductDto dto) {
		ProductDto dto2 = service.getProd(dto.getNum());
		dto2.setName(dto.getName());
		dto2.setPrice(dto.getPrice());
		dto2.setAmount(dto.getAmount());
		service.saveProd(dto2);
		return "redirect:/prod/list";
	}
	
	@PostMapping("/edit-img")
	public String editImg(MultipartFile f, int num) {
		// 이미지 변경할 상품을 검색해서 전체 정보를 불러옴
		ProductDto prod = service.getProd(num);

		// 삭제할 원본 이미지 경로 변수에 저장
		String delFileName = path + prod.getImg();
		System.out.println("delFileName:" + delFileName);

		// 삭제할 파일의 File 객체를 생성
		File delFile = new File(delFileName);

		// delete(): 파일 삭제 메서드
		delFile.delete();

		// 새로 올라온 파일의 원파일명 저장
		String fname = f.getOriginalFilename();

		// 중복을 막기위해 원본파일명 앞에 상품번호를 붙임
		fname = prod.getNum() + fname;

		// 서버에 복사할 새 파일을 생성
		File newf = new File(path + fname);
		try {

			// 올라온 파일의 내용을 생성한 새파일에 복사
			f.transferTo(newf);// 업로드 파일을 newf에 복사

			// 변경된 이미지 경로를 수정 객체에 저장
			prod.setImg(fname);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// save()로 db에서 수정
		service.saveProd(prod);

		return "redirect:/prod/detail?num=" + num;
	}
}


