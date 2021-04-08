package org.judy.notice.mapper;

import java.util.List;

import org.judy.common.util.NoticeFileDTO;

public interface NoticeFileMapper {
	
	void insertFile(NoticeFileDTO file);
	
	void deleteFile(Integer nno);
	
	List<NoticeFileDTO> getFile(Integer nno);

}
