package org.judy.store.service;

import org.judy.store.domain.DocumentFile;
import org.judy.store.dto.StoreDTO;
import org.judy.store.mapper.StoreMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class StoreServiceImpl implements StoreService {

	private final StoreMapper mapper;
	
	@Override
	public StoreDTO getStore(String mid) {
		// TODO Auto-generated method stub
		StoreDTO dto = toDTO(mapper.getStore(mid));
		
		log.info("getStore........");
		return dto;
	}

}
