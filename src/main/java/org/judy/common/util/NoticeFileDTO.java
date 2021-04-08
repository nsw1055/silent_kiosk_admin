package org.judy.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeFileDTO {

	private String uploadPath;
	private String uuid;
	private String fileName;
	private boolean image;
	
	private Integer nno;
	
	public String getLink() {

		String tempLink = fileName.replace(".", "#");

		String fileLink = uploadPath + "/"+ uuid + "_" + tempLink;
		String str = "";

		try {
			str = URLEncoder.encode(fileLink, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println("");

		return str;
	}

	public String getthumbLink() {

		String tempLink = fileName.replace(".", "#");

		String thumbFileLink = uploadPath + "/s_" + uuid + "_" + tempLink;

		String thumbStr = "";

		try {
			thumbStr = URLEncoder.encode(thumbFileLink, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return thumbStr;

	}

	public NoticeFileDTO(String uploadPath, String uuid, String fileName, boolean image) {
		super();
		this.uploadPath = uploadPath;
		this.uuid = uuid;
		this.fileName = fileName;
		this.image = image;
	}

}
