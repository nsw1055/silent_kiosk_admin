package org.judy.manager.mapper;

import org.judy.common.config.CommonConfig;
import org.judy.common.util.PageDTO;
import org.judy.manager.config.ManagerConfig;
import org.judy.manager.domain.Manager;
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
public class ManagerMapperTests {

	@Autowired
	private ManagerMapper mapper;
	
	
	@Test
	public void testGetMemberList() {
		PageDTO pageDTO = PageDTO.builder().page(1).perSheet(10).type(null).keyword(null).build();
		
		log.info(mapper.getManagerList(pageDTO));
	}
	@Test
	public void testDelMemberList() {
		PageDTO pageDTO = PageDTO.builder().page(1).perSheet(10).type(null).keyword(null).build();
		
		log.info(mapper.delManagerList(pageDTO));
	}
	
	@Test
	public void testappMemberList() {
		PageDTO pageDTO = PageDTO.builder().page(1).perSheet(10).type(null).keyword(null).build();
		
		log.info(mapper.appManagerList(pageDTO));
	}
	
	@Test
	public void testSelectOne() {
		log.info(mapper.selectOne("user147"));
	}
	
	@Test
	public void testInsert() {
		log.info("insert.....");
		for (int i = 301; i <= 400; i++) {
			Manager manager = Manager.builder().mid("user"+i)
											.mpw("1234")
											.email("asdf@asdf.asd")
											.phone("01012345678")
											.cdn("C:\\upload\\temp\\ksdjflks.jpg")
											.health("C:\\upload\\temp\\awqdfs.jpg")
											.hygiene("C:\\upload\\temp\\12345lks.jpg")
											.license("C:\\upload\\temp\\uw325flks.jpg")
											.build();
											
		mapper.registerMan(manager);
		}		
	}
	
	@Test
	public void testTotal() {
		PageDTO pageDTO = PageDTO.builder().type("s").keyword("후").build();
		log.info(mapper.totalMan(pageDTO));
	}
	
	@Test
	public void testEnabled() {
		log.info(mapper.enabled("user147"));
	}
	
	@Test
	public void testApproval() {
		mapper.approval("user147");
		log.info(mapper.selectOne("user147"));
	}
}
