package org.judy.manager.controller;

import java.util.List;

import org.judy.common.util.PageDTO;
import org.judy.common.util.PageMaker;
import org.judy.manager.dto.ManagerDTO;
import org.judy.manager.service.ManagerService;
import org.judy.store.dto.StoreDTO;
import org.judy.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
@Log4j
public class ManagerController {

	private final ManagerService managerService;
	
	private final StoreService storeService;
	
	
	@GetMapping("/list")
	public void getList(PageDTO pageDTO, Model model ) {
//		log.info("getList...............");
		PageDTO dto = PageDTO.builder().page(pageDTO.getPage()).perSheet(pageDTO.getPerSheet()).type(pageDTO.getType()).keyword(pageDTO.getKeyword()).build();
		
		PageMaker pageMaker= new PageMaker(pageDTO, managerService.totalMan(dto));
		
		List<ManagerDTO> listMan = managerService.getManagerList(pageDTO);
		
//		model.addAttribute("pageDTO" , dto);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list" , listMan);
		
	}
	
	@GetMapping("/delList")
	public void delList(PageDTO pageDTO, Model model ) {
//		log.info("getList...............");
		PageDTO dto = PageDTO.builder().page(pageDTO.getPage()).perSheet(pageDTO.getPerSheet()).type(pageDTO.getType()).keyword(pageDTO.getKeyword()).build();
		
		PageMaker pageMaker= new PageMaker(pageDTO, managerService.totalMan(dto));
		
		List<ManagerDTO> listMan = managerService.delManagerList(pageDTO);
		
//		model.addAttribute("pageDTO" , dto);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list" , listMan);
		
	}
	
	@GetMapping("/appList")
	public void appList(PageDTO pageDTO, Model model ) {
//		log.info("getList...............");
		PageDTO dto = PageDTO.builder().page(pageDTO.getPage()).perSheet(pageDTO.getPerSheet()).type(pageDTO.getType()).keyword(pageDTO.getKeyword()).build();
		
		PageMaker pageMaker= new PageMaker(pageDTO, managerService.totalMan(dto));
		
		List<ManagerDTO> listMan = managerService.appManagerList(pageDTO);
		
//		model.addAttribute("pageDTO" , dto);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list" , listMan);
		
	}
	
	@GetMapping("/read")
	public void getRead(String mid,  Model model) {
		
		ManagerDTO managerDTO = managerService.selectOne(mid);
		StoreDTO storeDTO = storeService.getStore(mid);
		
		log.info("getList...............");
		
		model.addAttribute("manager" ,managerDTO);
		model.addAttribute("store" ,storeDTO);		
//		model.addAttribute("list" , service.getMemberList());
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteMan(@RequestBody String mid) {
		
		managerService.enabled(mid);
		
		
		return new ResponseEntity<String>("success" , HttpStatus.OK);
	}
	
	@PostMapping("/approval")
	public ResponseEntity<String> approvalMan(@RequestBody String mid){
		
		managerService.approval(mid);
		
		return new ResponseEntity<String>("sccuss", HttpStatus.OK);
	}
	
	@GetMapping("/register")
	public void getRegister() {
		
	}
	
	@PostMapping("/register")
	public void postRegister() {
		
			
	}
	
	
	
}
