package org.judy.notice.service;

import java.util.List;

import org.judy.common.util.PageDTO;
import org.judy.notice.domain.Notice;
import org.judy.notice.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeDTO> getList(PageDTO pageDTO);
	
	NoticeDTO getOne(Integer nno);
	
	void insert(NoticeDTO dto);
	
	int getTotal(PageDTO pageDTO);
	
	void delete(Integer nno);
	
	default NoticeDTO toDTO(Notice vo) {
		
		NoticeDTO dto = new NoticeDTO();
		
		dto.setNno(vo.getNno());
		dto.setTitle(vo.getTitle());
		dto.setContent(vo.getContent());
		dto.setWriter(vo.getWriter());
		dto.setRegdate(vo.getRegdate());
		dto.setUpdatedate(vo.getUpdatedate());
		dto.setShow(vo.getShow());
		dto.setCategory(vo.getCategory());
		
		return dto;
	}
	
	
	default Notice toDomain(NoticeDTO dto) {
		
		Notice vo = Notice.builder()
				.nno(dto.getNno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.regdate(dto.getRegdate())
				.updatedate(dto.getUpdatedate())
				.show(dto.getShow())
				.category(dto.getCategory())
				.build();
		
		return vo;	
	}
	
}
