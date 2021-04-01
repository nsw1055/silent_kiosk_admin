package org.judy.store.service;

import java.util.List;

import org.judy.store.domain.DocumentFile;
import org.judy.store.domain.Store;
import org.judy.store.dto.StoreDTO;


public interface StoreService {
	
	StoreDTO getStore(String mid);
	
	void deleteDoc(String muuid);
	
	
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
	
	default StoreDTO toDTO(Store store, List<DocumentFile> docFiles) {
		StoreDTO dto = StoreDTO.builder()
								.mid(store.getMid())
								.sname(store.getSname())
								.lat(store.getLat())
								.lng(store.getLng())
								.address(store.getAddress())
								.category(store.getCategory())
								.docFiles(docFiles)
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
