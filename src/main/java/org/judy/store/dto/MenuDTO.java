package org.judy.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDTO {

	private Integer mno,sno, cno;
	private String menuName, content, mimg;
	private String mprice;
	
}
