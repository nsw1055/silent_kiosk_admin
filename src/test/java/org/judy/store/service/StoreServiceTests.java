package org.judy.store.service;

import org.judy.common.config.CommonConfig;
import org.judy.manager.config.ManagerConfig;
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
	
}
