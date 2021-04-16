package org.judy.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerFileDTO {

	private String uploadPath;
	private String uuid;
	private String fileName;
	private Boolean image;

	public String getLink() {

		String fileLinkName = fileName.replace(".", "#");

		String link = uploadPath + "/" + fileLinkName;

		String encodingLink = "";

		try {
			encodingLink = URLEncoder.encode(link, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return encodingLink;
	}

	public String getThumbLink() {

		if (image) {

			String fileLinkName = fileName.replace(".", "#");

			String thumbLink = uploadPath + "/s_" + uuid + "_" + fileLinkName;

			String encodingThumbLink = "";

			try {
				encodingThumbLink = URLEncoder.encode(thumbLink, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			return encodingThumbLink;
		}
		
		return null;
	}
}
