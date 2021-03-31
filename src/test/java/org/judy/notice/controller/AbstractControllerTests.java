package org.judy.notice.controller;

import org.judy.common.config.CommonConfig;
import org.judy.common.config.ServletConfig;
import org.judy.notice.config.NoticeConfig;
import org.judy.time.config.TimeConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, ServletConfig.class,TimeConfig.class, NoticeConfig.class})
@Log4j
@WebAppConfiguration
public class AbstractControllerTests {
	
	@Autowired
	WebApplicationContext ctx;
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		log.info("setup.......");
	}
}
