package org.judy.notice.controller;

import org.judy.common.util.PageDTO;
import org.judy.common.util.PageMaker;
import org.judy.notice.dto.NoticeDTO;
import org.judy.notice.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void getList(PageDTO pageDTO, Model model) {
		
		PageMaker pageMaker = new PageMaker(pageDTO, service.getTotal(pageDTO));
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", service.getList(pageDTO));
		
	}

	@GetMapping("/read")
	public void getOne(@ModelAttribute("nno") Integer nno, PageDTO pageDTO, Model model) {
		
		model.addAttribute("notice", service.getOne(nno));		
	}
	
	@GetMapping("/register")
	public void getInsert(PageDTO pageDTO) {
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> postInsert(@RequestBody NoticeDTO dto) {

		log.info("insert.................");
		log.info("------------------");
		log.info(dto);
		log.info("------------------");

		service.insert(dto);
		
		return new ResponseEntity<String>("success", HttpStatus.OK); 

	}

	@PostMapping("/")
	public void modify() {

	}

	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<String> delete(Integer nno) {

		log.info("delete................");
		
		log.info("------------------");
		log.info(nno);
		log.info("------------------");

		service.delete(nno);

		return new ResponseEntity<String>("success", HttpStatus.OK);

	}

}
