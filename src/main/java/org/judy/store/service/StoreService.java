package org.judy.store.service;

import org.judy.store.domain.DocumentFile;
import org.judy.store.domain.Store;
import org.judy.store.dto.StoreDTO;


public interface StoreService {
	
	StoreDTO getStore(String mid);
	
	
	default StoreDTO toDTO(Store store) {
		StoreDTO dto = StoreDTO.builder()
								.mid(store.getMid())
								.sname(store.getSname())
								.lat(store.getLat())
								.lng(store.getLng())
								.address(store.getAddress())
								.category(store.getCategory())
								.build();
		return dto;
	}
	
	default Store toDomain(StoreDTO dto) {
		Store store = Store.builder()
				.mid(dto.getMid())
				.sname(dto.getSname())
				.lat(dto.getLat())
				.lng(dto.getLng())
				.address(dto.getAddress())
				.category(dto.getCategory())
				.build();
		
	return store;
	
	}
	
}
