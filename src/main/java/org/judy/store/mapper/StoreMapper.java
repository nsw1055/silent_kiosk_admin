package org.judy.store.mapper;

import java.util.List;

import org.judy.store.domain.DocumentFile;
import org.judy.store.domain.Store;

public interface StoreMapper {

	public Store getStore(String mid);
	
	public List<DocumentFile> findByMid(String mid);
	
	public void deleteDoc(String muuid);
}
