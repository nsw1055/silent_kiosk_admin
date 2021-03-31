package org.judy.manager.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManagerDTO {


	String mid, mpw;
	
	String email;
	
	String phone;
	
	String sname;
	@Default
	Boolean enabled = false;
	@Default
	Boolean approval = false;
	
	String logoImg;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date regdate, updatedate;
	
	
	
}
