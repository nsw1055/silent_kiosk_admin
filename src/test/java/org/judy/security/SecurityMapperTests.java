package org.judy.security;

import org.judy.common.security.mapper.MemberMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.judy.common.config.CommonConfig.class, org.judy.common.config.SecurityConfig.class})
@Log4j
public class SecurityMapperTests {
	
	@Autowired
	MemberMapper mapper;
	
	@Test
	public void testMember() {
		
		log.info(mapper.read("test1"));
		
	}
	
	
	@Test
	public void testManager() {
		
		log.info(mapper.managerRead("user7"));
		
	}

}
