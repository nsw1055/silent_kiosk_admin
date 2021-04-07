package org.judy.manager.service;

import org.judy.common.config.CommonConfig;
import org.judy.common.util.PageDTO;
import org.judy.manager.config.ManagerConfig;
import org.judy.manager.dto.ManagerDTO;
import org.judy.store.config.StoreConfig;
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
public class ManagerServiceTests {

	@Autowired
	private ManagerService service;
	
	
	@Test
	public void getManagerTest() {
		
		PageDTO pageDTO = PageDTO.builder().page(1).perSheet(10).type(null).keyword(null).build();
		
		log.info("------------");
		log.info(service);
		log.info(service.getManagerList(pageDTO));
	}
	
	@Test
	public void testAppManagerTest() {
		
		PageDTO pageDTO = PageDTO.builder().page(1).perSheet(10).type(null).keyword(null).build();
		
		log.info("------------");
		log.info(service);
		log.info(service.appManagerList(pageDTO));
	}
	
	@Test
	public void testSelectOne() {
		log.info(service.selectOne("테스트1"));
	}
	@Test
	public void testTotal() {
		PageDTO pageDTO = PageDTO.builder().page(1).perSheet(10).type("s").keyword("후").build();
		log.info(service.totalMan(pageDTO));
		
	}
	
	@Test
	public void testEnabled() {
		service.enabled("user147");
		log.info(service.selectOne("user147"));
	}
	
	@Test
	public void testApproval() {
		service.approval("user147");
		log.info(service.selectOne("user147"));
	}
	
	@Test
	public void testRegister() {
		
		ManagerDTO dto = ManagerDTO.builder().mid("user401").mpw("123123123").phone("010201929").email("sdjflkdsj@gmail.com").cdn("C:\\upload\\temp\\ksdjflks.jpg")
				.health("C:\\upload\\temp\\awqdfs.jpg")
				.hygiene("C:\\upload\\temp\\12345lks.jpg")
				.license("C:\\upload\\temp\\uw325flks.jpg")
				.enabled(true)
				.approval(true)
				.build();
		service.registerMan(dto);
	}
	
}
