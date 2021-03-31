package org.judy.notice.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.log4j.Log4j;

@Log4j
public class NoticeControllerTests extends AbstractControllerTests {

	@Test
	public void testList() throws Exception {
		log.info(mockMvc);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/notice/list").param("page", "2").param("perSheet", "10")).andDo(print());

	}
	
	@Test
	public void testOne() throws Exception {
		log.info(mockMvc);
		
		mockMvc.perform(
				MockMvcRequestBuilders
				.get("/notice/read").param("nno", "20")).andDo(print());
	}
	
	@Test
	public void testInsert() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.post("/notice/register")
				.param("title", "테스트")
				.param("context", "내용")
				.param("writer", "user00"))
				.andDo(print());
		
	}

}
