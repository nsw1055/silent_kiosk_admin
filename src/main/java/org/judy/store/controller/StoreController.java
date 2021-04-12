package org.judy.store.controller;

import org.judy.manager.service.ManagerService;
import org.judy.store.dto.MenuDTO;
import org.judy.store.dto.ToppingDTO;
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
@RequestMapping("/store")
@RequiredArgsConstructor
@Log4j
public class StoreController {
	
	private final ManagerService managerService;
	
	private final StoreService storeService;
	
	@GetMapping("/read")
	public void getStore() {
		
	}
	
	@GetMapping("/menuList")
	public void getMenu(Integer sno, Integer cno, Model model) {
		
		model.addAttribute("menu", storeService.getMenu(sno, cno));
		
	}
	
	@GetMapping("/toppingList")
	public void getTopping(Integer sno, Model model) {
		
		model.addAttribute("topping", storeService.getTopping(sno)); 
		
	}
	
	@GetMapping("/menuModify")
	public void getMenuModify(Integer mno , Model model) {
		
		model.addAttribute("menu" , storeService.getOneMenu(mno));
		
	}
	
	@PostMapping("/menuModify")
	public ResponseEntity<String> postMenuModify(@RequestBody MenuDTO menuDTO) {
		log.info("update.............");
		storeService.updateMenu(menuDTO);
		
		return new ResponseEntity<String>("success" , HttpStatus.OK);
	}
	
	@PostMapping("/menuDelete")
	   public ResponseEntity<String> postMenuDelete(@RequestBody Integer mno){
	      storeService.delMenu(mno);
	      return new ResponseEntity<String>("success" , HttpStatus.OK);
	   }
	
	@GetMapping("/toppingModify")
	public void getToppingModify(Integer tno , Model model) {
		
		model.addAttribute("topping" , storeService.getOneTopping(tno));
		
	}
	
	@PostMapping("/toppingModify")
	public ResponseEntity<String> postTopModify(@RequestBody ToppingDTO toppingDTO) {
		log.info("update.............");
		
		storeService.updateTop(toppingDTO);
		
		return new ResponseEntity<String>("success" , HttpStatus.OK);
	}
	
	@PostMapping("/toppingDelete")
	   public ResponseEntity<String> postTopDelete(@RequestBody Integer tno){
	      storeService.delTop(tno);
	      return new ResponseEntity<String>("success" , HttpStatus.OK);
	   }
}
