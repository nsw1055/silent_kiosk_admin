package org.judy.manager.domain;

import java.sql.Date;
import java.util.List;

import org.judy.common.security.domain.AuthVO;
import org.judy.store.domain.Store;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Manager {

	String mid, mpw;
	
	String email;
	
	String phone;
	
	@Default
	Boolean enabled = false;
	@Default
	Boolean approval = false;
	
	String cdn,health,hygiene,license;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date regdate, updatedate;
	
	List<Store> storeList;
	
	List<AuthVO> authList;
	
}
