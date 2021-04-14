package org.judy.store.mapper;

import org.judy.common.config.CommonConfig;
import org.judy.manager.config.ManagerConfig;
import org.judy.store.config.StoreConfig;
import org.judy.store.domain.Menu;
import org.judy.store.domain.Topping;
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
public class StoreMapperTests {

	@Autowired
	private StoreMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper.getStore("user154"));
	}
	
	@Test
	public void testDelete() {
		mapper.deleteDoc("08051d27-c76b-481a-a943-b406c939d270");
	}
	
	// MENU
	
	@Test
	public void testGetMenu() {
		log.info(mapper.getMenu(10,1));
	}
	
	@Test
	public void testGetOneMenu() {
		log.info(mapper.getOneMenu(1));
	}
	
	@Test
	public void testInsertMenu() {
		Menu menu = Menu.builder().sno(10).menuName("냉까스").content("신메뉴").mprice("8000").mimg("냉까스사진").cno(2).build();
		mapper.insertMenu(menu);
	}
	
	@Test
	public void testDelMenu() {
		mapper.delMenu(11);
		log.info(mapper.getMenu(10,1));
	}
	
	@Test
	public void testUpdateMenu() {
		Menu menu = Menu.builder().mno(12).menuName("냉까스").content("신메뉴").mprice("9000").mimg("냉까스사진").cno(2).build();
		mapper.updateMenu(menu);
	
	}
	
	// TOPPING
	
	@Test
	public void testGetTopping() {
		log.info(mapper.getTopping(10));
	}
	
	@Test
	public void testGetOneTopping() {
		log.info(mapper.getOneTopping(8));
	}
	
	@Test
	public void testInsertTop() {
		Topping topping = Topping.builder().sno(10).tname("짬뽕국물").tprice("100").timg("짬뽕사진").build();
		mapper.insertTop(topping);
	}
	
	@Test
	public void testdelTop() {
		mapper.delTop(13);
		log.info(mapper.getTopping(10));
	}
	
	@Test
	public void testUpdateTop() {
		Topping topping = Topping.builder().tno(14).tname("오뎅국물").tprice("100").timg("오뎅사진").build();
		mapper.updateTop(topping);
	}
}
