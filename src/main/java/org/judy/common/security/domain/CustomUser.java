package org.judy.common.security.domain;

import java.util.stream.Collectors;

import org.judy.manager.domain.Manager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	
	private MemberVO member;
	
	private Manager manager;

	public CustomUser(MemberVO vo) {
		super(vo.getMid(), vo.getMpw(), vo.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		
		this.member = vo;
	}

	
	
	

}
