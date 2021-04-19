package org.judy.store.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.judy.manager.service.ManagerService;
import org.judy.store.dto.MenuDTO;
import org.judy.store.dto.MenuToppingDTO;
import org.judy.store.dto.StoreDTO;
import org.judy.store.dto.ToppingDTO;
import org.judy.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
@Log4j
public class StoreController {
	
	private final ManagerService managerService;
	
	private final StoreService storeService;
	
	@GetMapping("/Sample")
	public void sample() {
	      
	}
	   
	@PostMapping("/jusoPopup")
	public String postJuso(){
	   
	   return "/store/jusoPopup";
	}
	   
	@GetMapping("/jusoPopup")
	public void juso() {
	      
	}
	  
	@GetMapping("/read")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','principal.username == #mid')")
	public void getStore(String mid , Model model) {
		model.addAttribute("store" , storeService.getStore(mid));
		model.addAttribute("manager" , managerService.selectOne(mid));
	}
	
	@GetMapping("/register")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public void getRegister(String mid , Model model) {
		model.addAttribute("mid" , mid);
		
	}
	
	@PostMapping("/register")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public ResponseEntity<String> PostRegister(@Validated @RequestBody StoreDTO storeDTO , BindingResult result) {
		
		if(result.hasErrors()) {
			return new ResponseEntity<String>("fail" , HttpStatus.OK);
		}
		Integer sno = storeService.insertStore(storeDTO);
			
		String logoPath = "C:\\upload\\admin\\manager\\logoImg\\"+sno;
		String storePath = "C:\\upload\\admin\\manager\\storeImg\\"+sno;
		
		File logoUploadPath = new File(logoPath);
		File storeUploadPath = new File(storePath);
		
		
		if (logoUploadPath.exists() == false) {
			logoUploadPath.mkdirs();
		}
		
		if (storeUploadPath.exists() == false) {
			storeUploadPath.mkdirs();
		}
		
		copyLogo(sno, storeDTO.getLogoImg(), "logoImg");
		
		storeDTO.getFileDTO().forEach(files -> {
			String file = files.getSuuid() +"_"+ files.getSfileName();
			copyLogo(sno , file, "storeImg");
		});
		
		storeDTO.getFileDTO().forEach(files -> {
			String file = "s_"+files.getSuuid() +"_"+ files.getSfileName();
			copyLogo(sno , file, "storeImg");
		});
		
		
		return new ResponseEntity<String>("success" , HttpStatus.OK);
		
	}

	@GetMapping("/modify")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','principal.username == #mid')")
	public void getModify(String mid, Integer sno , Model model) {
		model.addAttribute("mid" , mid);
		model.addAttribute("store", storeService.getStoreOne(sno));
		model.addAttribute("storeImg" , storeService.getStoreImg(sno));
		
	}
	
	@PostMapping("/modify")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','principal.username == #storeDTO.mid')")
	public ResponseEntity<String> postModify(@RequestBody StoreDTO storeDTO) {
		
		storeService.updateStore(storeDTO);
		
		String logoPath = "C:\\upload\\admin\\manager\\logoImg\\"+storeDTO.getSno();
		String storePath = "C:\\upload\\admin\\manager\\storeImg\\"+storeDTO.getSno();
		
		File logoUploadPath = new File(logoPath);
		File storeUploadPath = new File(storePath);
		
		
		if (logoUploadPath.exists() == false) {
			logoUploadPath.mkdirs();
		}
		
		if (storeUploadPath.exists() == false) {
			storeUploadPath.mkdirs();
		}
		
		copyLogo(storeDTO.getSno(), storeDTO.getLogoImg(), "logoImg");
		
		storeDTO.getFileDTO().forEach(files -> {
			String file = files.getSuuid() +"_"+ files.getSfileName();
			copyLogo(storeDTO.getSno() , file, "storeImg");
		});
		
		storeDTO.getFileDTO().forEach(files -> {
			String file = "s_"+files.getSuuid() +"_"+ files.getSfileName();
			copyLogo(storeDTO.getSno() , file, "storeImg");
		});
		
		

		return new ResponseEntity<String>("success" , HttpStatus.OK);
		
	}
	
	@PostMapping("/delete")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','principal.username == #mid')")
	public ResponseEntity<String> postDelete(@RequestBody Integer sno){
		
		storeService.delStore(sno);
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@GetMapping("/menuList")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','principal.username == #mid')")
	public void getMenu(Integer sno, Integer cno, Model model) {
		
		model.addAttribute("menu", storeService.getMenu(sno, cno));
		model.addAttribute("sname", storeService.menuSname(sno));	
	}
	
	
	@PostMapping("/menuRegister")
	public ResponseEntity<String> postMenuRegister(@RequestBody MenuDTO menuDTO) {
		
		Integer mno = storeService.insertMenu(menuDTO);
		
		String path = "C:\\upload\\admin\\manager\\MImg\\"+menuDTO.getSno()+"\\"+mno;
		
		File uploadPath = new File(path);
		
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		copyImg(menuDTO.getSno(), mno, menuDTO.getMimg() , "MImg");
		
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@GetMapping("/menuModify")
	public void getMenuModify(Integer mno , Model model) {
		
		model.addAttribute("menu" , storeService.getOneMenu(mno));
		
	}
	
	@PostMapping("/menuModify")
	public ResponseEntity<String> postMenuModify(@RequestBody MenuDTO menuDTO) {
		log.info("update.............");
		storeService.updateMenu(menuDTO);
		
		String path = "C:\\upload\\admin\\manager\\MImg\\"+menuDTO.getSno()+"\\"+menuDTO.getMno();
		
		File uploadPath = new File(path);
		
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		copyImg(menuDTO.getSno(), menuDTO.getMno(), menuDTO.getMimg() , "MImg");
		
		return new ResponseEntity<String>("success" , HttpStatus.OK);
	}
	
	@PostMapping("/menuDelete")
	   public ResponseEntity<String> postMenuDelete(@RequestBody Integer mno){
	      storeService.delMenu(mno);
	      return new ResponseEntity<String>("success" , HttpStatus.OK);
	   }
	
	@GetMapping("/toppingList")
	public void getTopping(Integer sno, Model model) {
		
		model.addAttribute("topping", storeService.getTopping(sno)); 
		model.addAttribute("sname", storeService.menuSname(sno));
		
	}
	
	@PostMapping("/toppingRegister")
	public ResponseEntity<String> postToppingRegister(@RequestBody ToppingDTO toppingDTO) {
		
		Integer tno = storeService.insertTop(toppingDTO);
		
		String path = "C:\\upload\\admin\\manager\\tImg\\"+toppingDTO.getSno()+"\\"+tno;
		
		File uploadPath = new File(path);
		
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		copyImg(toppingDTO.getSno(), tno, toppingDTO.getTimg() , "tImg");
		
		
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@GetMapping("/toppingModify")
	public void getToppingModify(Integer tno , Model model) {
		
		model.addAttribute("topping" , storeService.getOneTopping(tno));
		
		
	}
	
	@PostMapping("/toppingModify")
	public ResponseEntity<String> postTopModify(@RequestBody ToppingDTO toppingDTO) {
		log.info("update.............");
		
		storeService.updateTop(toppingDTO);
		
		String path = "C:\\upload\\admin\\manager\\tImg\\"+toppingDTO.getSno()+"\\"+toppingDTO.getTno();
		
		File uploadPath = new File(path);
		
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		copyImg(toppingDTO.getSno(), toppingDTO.getTno(), toppingDTO.getTimg() , "tImg");
		
		return new ResponseEntity<String>("success" , HttpStatus.OK);
	}
	
	@PostMapping("/toppingDelete")
	   public ResponseEntity<String> postTopDelete(@RequestBody Integer tno){
	      storeService.delTop(tno);
	      return new ResponseEntity<String>("success" , HttpStatus.OK);
	   }
	
	@GetMapping("/selectedTop")
	public ResponseEntity<List<ToppingDTO>> getSelectedTop(Integer mno){
		
		return new ResponseEntity<List<ToppingDTO>>(storeService.selectedTop(mno) , HttpStatus.OK);
	}
	
	@GetMapping("/unSelectTop")
	public ResponseEntity<List<ToppingDTO>> getUnSelectTop(Integer mno, Integer sno){
		MenuDTO menuDTO = MenuDTO.builder().sno(sno).mno(mno).build();
		
		return new ResponseEntity<List<ToppingDTO>>(storeService.unSelectTop(menuDTO) , HttpStatus.OK);
	}
	
	@PostMapping("/exceptTopping")
	public ResponseEntity<String> postExceptTop(@RequestBody MenuToppingDTO menuToppingDTO){
		
		storeService.exceptTop(menuToppingDTO);
		
		return new ResponseEntity<String>("success" , HttpStatus.OK);
	}
	
	@PostMapping("/addTopping")
	public ResponseEntity<String> postAddTop(@RequestBody MenuToppingDTO menuToppingDTO){
		
		storeService.addTop(menuToppingDTO);
		
		return new ResponseEntity<String>("success" , HttpStatus.OK);
	}
	
	
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);

	}
	
	private void copyLogo(Integer sno, String file, String midPath) {
		File tempFile = new File("C:\\upload\\temp\\admin\\manager\\"+getFolder()+"\\"+file);
		log.info(tempFile);
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(tempFile);
			log.info("dto.getCdn: "+file);
			byte[] buffer = new byte[inputStream.available()];
		    inputStream.read(buffer);

		    File targetFile = new File("C:\\upload\\admin\\manager\\"+midPath+"\\"+sno+"\\"+file);
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    
		    inputStream.close();
		    outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			tempFile.delete();
	}
	
	private void copyImg(Integer sno, Integer num, String file , String midPath) {
		File tempFile = new File("C:\\upload\\temp\\admin\\manager\\"+getFolder()+"\\"+file);
		log.info(tempFile);
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(tempFile);
			log.info("dto.getCdn: "+file);
			byte[] buffer = new byte[inputStream.available()];
		    inputStream.read(buffer);

		    File targetFile = new File("C:\\upload\\admin\\manager\\"+midPath+"\\"+sno+"\\"+num+"\\"+file);
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    
		    inputStream.close();
		    outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			tempFile.delete();
	}
	
}
