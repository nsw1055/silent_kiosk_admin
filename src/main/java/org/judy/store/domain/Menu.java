package org.judy.store.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {

	private Integer mno,sno, cno;
	private String menuName, mprice,content, mimg;
	
}
