package org.judy.common.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

	@Default
	private Integer page = 1;

	@Default
	private Integer perSheet = 10;
	
	private String type;
	
	private String keyword;
	

	public Integer getSkip() {

		return (page - 1) * perSheet;
	}
	
	public String[] getArr() {

		if(keyword == null||keyword.trim().length() == 0){
			return null;
		}
		
		if(type == null) {
			return null;
		}
		
		
		return type.split("");
	}
	
	
}
