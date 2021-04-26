package org.judy.notice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.judy.common.util.NoticeFileDTO;
import org.judy.common.util.PageDTO;
import org.judy.common.util.PageMaker;
import org.judy.notice.dto.NoticeDTO;
import org.judy.notice.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/notice")
@Log4j
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService service;

	@GetMapping("/list")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ADV')")
	public void getList(PageDTO pageDTO, Model model, HttpServletRequest res) {

		String auth = (String)res.getSession().getAttribute("auth");
		
		System.out.println("Controller:" +auth);
		

		PageMaker pageMaker = new PageMaker(pageDTO, service.getTotal(pageDTO));

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", service.getList(pageDTO));
		model.addAttribute("topList", service.topList());

	}

	@GetMapping("/read")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_ADV')")
	public void getOne(Integer nno, PageDTO pageDTO, Model model) {

		model.addAttribute("notice", service.getOne(nno));
	}

	@GetMapping("/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void getInsert(PageDTO pageDTO) {

	}

	@PostMapping("/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> postInsert(@RequestBody @Valid NoticeDTO dto, BindingResult bindingResult) {

		log.info("insert.................");
		log.info("------------------");
		log.info(dto);
		log.info("------------------");

		String path = "C:\\upload\\admin\\notice\\" + getFolder();

		File uploadPath = new File(path);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		dto.getList().forEach(file -> copyFile(file));

		log.info(bindingResult);

		if (bindingResult.hasErrors()) {

			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
		}

		service.insert(dto);

		return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);

	}

	@GetMapping("/modify")
	@PreAuthorize("principal.username == #writer")
	public void getModify(Integer nno, Model model, PageDTO pageDTO, String writer) {

		model.addAttribute("notice", service.getOne(nno));

	}

	@PostMapping("/modify")
	@PreAuthorize("principal.username == #dto.writer")
	public ResponseEntity<Object> modify(@RequestBody @Valid NoticeDTO dto, BindingResult bindingResult) {

		log.info("dto: " + dto);

		String path = "C:\\upload\\admin\\notice\\" + getFolder();

		File uploadPath = new File(path);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}

		dto.getList().forEach(file -> copyFile(file));

		if (bindingResult.hasErrors()) {

			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
		}

		service.update(dto);

		return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);

	}

	@PostMapping("/delete")
	@ResponseBody
	@PreAuthorize("principal.username == #writer")
	public ResponseEntity<String> delete(Integer nno, String writer) {

		log.info("delete................");

		log.info("------------------");
		log.info(nno);
		log.info("------------------");

		service.delete(nno);

		return new ResponseEntity<String>("success", HttpStatus.OK);

	}

	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);

	}

	private void copyFile(NoticeFileDTO file) {

		String originalFile = file.getUploadPath() + "\\" + file.getUuid() + "_" + file.getFileName();

		String sOriginalFile = file.getUploadPath() + "\\s_" + file.getUuid() + "_" + file.getFileName();

		makeCopyFile(originalFile);

		if (file.isImage()) {
			makeCopyFile(sOriginalFile);
		}
	}

	private void makeCopyFile(String originalFile) {

		File tempFile = new File("C:\\upload\\temp\\admin\\notice\\" + originalFile);
		try {
			if(tempFile.exists()) {
			FileInputStream inputStream = new FileInputStream(tempFile);
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);

			File targetFile = new File("C:\\upload\\admin\\notice\\" + originalFile);
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);

			outStream.close();
			inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tempFile.delete();

	}

}
