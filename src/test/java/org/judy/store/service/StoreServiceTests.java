package org.judy.store.service;

import org.judy.common.config.CommonConfig;
import org.judy.manager.config.ManagerConfig;
import org.judy.store.config.StoreConfig;
import org.judy.store.domain.Topping;
import org.judy.store.dto.MenuDTO;
import org.judy.store.dto.StoreDTO;
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
	
	@Test
	public void testInsert() {
		StoreDTO storeDTO = StoreDTO.builder().mid("testjs").address("서울시").category("일식").lat(123.123).lng(123.123).sname("테스트용").logoImg("C://").build();
		service.insertStore(storeDTO);
	}
	
	// MENU
	
	@Test
	public void testGetMenu() {
		log.info(service.getMenu(10,1)); 
	}
	
	@Test
	public void testGetOneMenu() {
		log.info(service.getOneMenu(1)); 
	}
	
	@Test
	public void testInsertMenu() {
		MenuDTO menuDTO = MenuDTO.builder().sno(10).menuName("생선까스").content("신메뉴").mprice("9000").mimg("생선까스사진").cno(2).build();
		service.insertMenu(menuDTO);
	}
	
	@Test
	public void testDelMenu() {
		service.delMenu(6);
	}
	
	@Test
	public void testUpdateMenu() {
		MenuDTO menuDTO = MenuDTO.builder().mno(12).menuName("냉까스").content("신메뉴").mprice("8000").mimg("냉까스사진").cno(2).build();
		service.updateMenu(menuDTO);
	}
	
	// TOPPING
	@Test
	public void testGetTopping() {
		log.info(service.getTopping(10)); 
	}
	
	@Test
	public void testGetOneTopping() {
		log.info(service.getOneTopping(8));
	}
	
	@Test
	public void testInsertTop() {
		ToppingDTO toppingDTO = ToppingDTO.builder().sno(10).tname("오뎅국물").tprice("10000").timg("오뎅사진").build();
		service.insertTop(toppingDTO);
	}
	
	@Test
	public void testDelTop() {
		service.delTop(12);
	}
	
	@Test
	public void testUpdateTopping() {
		ToppingDTO toppingDTO = ToppingDTO.builder().tno(14).tname("짬뽕국물").tprice("10000").timg("짬뽕사진").build();
		service.updateTop(toppingDTO);
	}
}
