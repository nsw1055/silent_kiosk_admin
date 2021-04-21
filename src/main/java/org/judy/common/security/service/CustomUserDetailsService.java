package org.judy.common.security.service;

import org.judy.common.security.domain.CustomUser;
import org.judy.common.security.domain.MemberVO;
import org.judy.common.security.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.warn("userName : "+username);
		
		MemberVO vo = mapper.read(username);
		
		log.warn(vo);

		return vo == null ? null : new CustomUser(vo);
	}

}
