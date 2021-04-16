package org.judy.notice.mapper;

import org.judy.common.config.CommonConfig;
import org.judy.common.util.PageDTO;
import org.judy.notice.config.NoticeConfig;
import org.judy.notice.domain.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, NoticeConfig.class})
@Log4j
public class NoticeMapperTests {
	
	@Autowired
	NoticeMapper mapper;
	
	@Test
	public void getListTest() {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setType(null);
		pageDTO.setKeyword(null);
		
		log.info(mapper.getList(pageDTO));
	}
	
	@Test
	public void topList() {
		log.info(mapper.topList());
	}
	
	@Test
	public void getOneTest() {
		log.info(mapper.getOne(20));	
	}
	
	@Test
	public void insertTest() {
		
		Notice vo = Notice.builder()
				.title("테스트제목")
				.content("테스트 내용")
				.writer("user00")
				.build();
				
		
		mapper.insert(vo);
		
	}
	
	@Test
	public void getTotalTest() {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setType(null);
		pageDTO.setKeyword(null);
		
		log.info(mapper.getTotal(pageDTO));
	}

	
	@Test
	public void deleteTest() {
		
		mapper.delete(505);
	}
	
	@Test
	public void getThumbTest() {
		
		log.info(mapper.getThumb(612));
	}
}
