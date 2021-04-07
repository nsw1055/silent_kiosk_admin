package org.judy.manager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	
	   @GetMapping("/Sample")
	   public void sample() {
	      
	   }
	   
	   @PostMapping("/jusoPopup")
	   public String postJuso(){
	   
	      return "/manager/jusoPopup";
	   }
	   
	   @GetMapping("/jusoPopup")
	   public void juso() {
	      
	   }
	
	
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
	public ResponseEntity<String> postRegister(@RequestBody ManagerDTO dto) {
		
		String path = "C:\\upload\\admin\\manager\\doc\\"+dto.getMid();
		
		File uploadPath = new File(path);
		
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		copyDoc(dto.getMid(), dto.getCdn());	    
		copyDoc(dto.getMid(), dto.getHealth());	    
		copyDoc(dto.getMid(), dto.getHygiene());	    
		copyDoc(dto.getMid(), dto.getLicense());	
		
		
		
		managerService.registerMan(dto);
		
		return new ResponseEntity<String>("Success" , HttpStatus.OK);
	}
	
	
	
	private File encoding(String link, String path) {

		File viewFile = null;

		try {
			String str = URLDecoder.decode(link, "UTF-8");

			String fileLink = str.replace("#", ".");

			viewFile = new File(path, fileLink);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewFile;
	}
	
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);

	}
	
	private void copyDoc(String mid, String file) {
		File tempFile = new File("C:\\upload\\temp\\admin\\manager\\"+getFolder()+"\\"+file);
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(tempFile);
			log.info("dto.getCdn: "+file);
			byte[] buffer = new byte[inputStream.available()];
		    inputStream.read(buffer);

		    File targetFile = new File("C:\\upload\\admin\\manager\\doc\\"+mid+"\\"+file);
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    
		    outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tempFile.delete();
	}
	
	
}
