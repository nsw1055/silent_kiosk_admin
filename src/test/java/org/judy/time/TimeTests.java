package org.judy.time;

import org.judy.common.config.CommonConfig;
import org.judy.time.config.TimeConfig;
import org.judy.time.mapper.TimeMapper;
import org.judy.time.service.TimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, TimeConfig.class})
@Log4j
public class TimeTests {

	@Autowired
	TimeMapper timeMapper;
	
	@Test
	public void testExist() {
		log.info("---------------");
		log.info(timeMapper);
		log.info(timeMapper.getNow());
		log.info(timeMapper.getNow2());
	}
	
	@Autowired
	TimeService timeService;
	
	@Test
	public void testService() {
		log.info(timeService.getTime());
	}
}
