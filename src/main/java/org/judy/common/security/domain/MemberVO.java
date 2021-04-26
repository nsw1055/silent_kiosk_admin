package org.judy.common.security.domain;

import java.util.List;

import org.judy.store.domain.Store;

import lombok.Data;

@Data
public class MemberVO {
	
	private String mid;
	private String mpw;
	private String mname;
	
	private Integer sno;
	private List<AuthVO> authList;
	

}
