package org.judy.store.dto;

import java.util.List;

import org.judy.store.domain.DocumentFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDTO {

	private String mid,sname;
	private Integer sno;
	private double lat,lng;
	private String address,category;
	
	private List<DocumentFile> docFiles;
	
}
