package org.judy.store.service;

import org.judy.common.config.CommonConfig;
import org.judy.manager.config.ManagerConfig;
import org.judy.store.config.StoreConfig;
import org.judy.store.domain.Topping;
import org.judy.store.dto.MenuDTO;
import org.judy.store.dto.ToppingDTO;
import org.judy.time.config.TimeConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, ManagerConfig.class , TimeConfig.class , StoreConfig.class})
@Log4j
public class StoreServiceTests {

	@Autowired
	private StoreService service;
	
	
	@Test
	public void testGetStore() {
		log.info(service.getStore("user150"));
	}
	
	@Test
	public void testDeleteDoc() {
		service.deleteDoc("08051d27-c76b-481a-a943-b406c939d270");
	}
	
	// MENU
	
	@Test
	public void testGetMenu() {
		log.info(service.getMenu(10,1)); 
	}
	
	@Test
	public void testInsertMenu() {
		MenuDTO menuDTO = MenuDTO.builder().sno(10).menuName("생선까스").content("신메뉴").mPrice("9000").mImg("생선까스사진").category("돈까스").build();
		service.insertMenu(menuDTO);
	}
	
	@Test
	public void testDelMenu() {
		service.delMenu(6);
	}
	
	@Test
	public void testUpdateMenu() {
		MenuDTO menuDTO = MenuDTO.builder().mno(12).menuName("냉까스").content("신메뉴").mPrice("8000").mImg("냉까스사진").category("돈까스").build();
		service.updateMenu(menuDTO);
	}
	
	// TOPPING
	@Test
	public void testGetTopping() {
		log.info(service.getTopping(10)); 
	}
	
	@Test
	public void testInsertTop() {
		ToppingDTO toppingDTO = ToppingDTO.builder().sno(10).tName("오뎅국물").tPrice("10000").tImg("오뎅사진").build();
		service.insertTop(toppingDTO);
	}
	
	@Test
	public void testDelTop() {
		service.delTop(12);
	}
	
	@Test
	public void testUpdateTopping() {
		ToppingDTO toppingDTO = ToppingDTO.builder().tno(14).tName("짬뽕국물").tPrice("10000").tImg("짬뽕사진").build();
		service.updateTop(toppingDTO);
	}
}
