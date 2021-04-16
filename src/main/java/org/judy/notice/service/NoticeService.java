package org.judy.notice.service;

import java.util.List;

import org.judy.common.util.NoticeFileDTO;
import org.judy.common.util.PageDTO;
import org.judy.notice.domain.Notice;
import org.judy.notice.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeDTO> getList(PageDTO pageDTO);
	
	List<NoticeDTO> topList();
	
	NoticeDTO getOne(Integer nno);
	
	void insert(NoticeDTO dto);
	
	int getTotal(PageDTO pageDTO);
	
	void delete(Integer nno);
	
	List<NoticeFileDTO> getFile(Integer nno);
	
	void update(NoticeDTO dto);
	
	NoticeFileDTO getThumb(Integer nno);
	
	default NoticeDTO toDTO(Notice vo) {
		
		NoticeDTO dto = new NoticeDTO();
		
		dto.setNno(vo.getNno());
		dto.setTitle(vo.getTitle());
		dto.setContent(vo.getContent());
		dto.setWriter(vo.getWriter());
		dto.setRegdate(vo.getRegdate());
		dto.setUpdatedate(vo.getUpdatedate());
		dto.setShowed(vo.getShowed());
		dto.setCategory(vo.getCategory());
		dto.setImg(vo.isImg());
		dto.setFile(vo.isFile());
		
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
				.showed(dto.getShowed())
				.category(dto.getCategory())
				.img(dto.isImg())
				.file(dto.isFile())
				.build();
		
		return vo;	
	}
	
}
