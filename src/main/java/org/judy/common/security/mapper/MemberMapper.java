package org.judy.common.security.mapper;

import org.judy.common.security.domain.MemberVO;
import org.judy.manager.domain.Manager;

public interface MemberMapper {
	
	MemberVO read(String Userid);
	
	Manager managerRead(String mid);
	

}
