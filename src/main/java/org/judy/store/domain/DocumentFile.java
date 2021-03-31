package org.judy.store.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentFile {

	private String muuid;
	private String mid;
	private String muploadPath;
	private String mfileName;
	private boolean mfileType;
	
}
