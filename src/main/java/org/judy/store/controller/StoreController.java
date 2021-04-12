package org.judy.store.controller;

import org.judy.manager.service.ManagerService;
import org.judy.store.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
