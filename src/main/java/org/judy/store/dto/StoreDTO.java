package org.judy.store.dto;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.judy.common.util.ManagerFileDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDTO {

	private String mid;
	@NotBlank(message = "not blank")
	private String sname;
	private Integer sno;
	private double lat,lng;
	private String address,category;
	private String logoImg;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date regdate, updatedate;
	
	@NotEmpty(message = "not Empty")
	private List<ManagerFileDTO> fileDTO;
	
}
