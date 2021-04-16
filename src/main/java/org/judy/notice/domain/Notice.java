package org.judy.notice.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
	private Boolean showed;
	private String category;
	private boolean img;
	private boolean file;
	private Date regdate, updatedate;
}
