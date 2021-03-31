package org.judy.notice.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
	
	private Integer nno;
	private String title;
	private String content;
	private String writer;
	private Boolean show;
	private String category;
	private Date regdate, updatedate;
	
}
