package org.judy.secyruty;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.judy.common.config.CommonConfig.class, org.judy.common.config.SecurityConfig.class})
@Log4j
public class PasswordEncoderTests {

	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Test
	public void testEncode() {
		
		String str = "1234";
		
		String enStr = pwEncoder.encode(str);
		
		//$2a$10$hsxysOLb41KghdZjDXaL0.uHHWVX0gM.07rpgmlIEDiPOs37yGgqq
		//$2a$10$4kT43wpBCsWhfLWqjPJka.j0mZuw0HnsrXHdC9S.AcuCJ7cuH5Q5a
		log.info(enStr);
		
	}
	
}
