package org.judy.notice.dto;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.judy.common.util.NoticeFileDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private Integer nno;
	@NotBlank(message = "제목을 입력하세요")
	private String title;
	@NotBlank(message = "내용을 입력하세요")
	private String content;
	private String writer;
	private Boolean showed;
	private String category;
	private boolean img;
	private boolean file;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date regdate, updatedate;
	private ArrayList<NoticeFileDTO> list;

}
