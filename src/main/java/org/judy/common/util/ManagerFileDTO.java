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

	private String suploadPath;
	private String suuid;
	private String sfileName;
	private Integer sno;

	public String getLink() {

		String fileLinkName = sfileName.replace(".", "#");

		String link = suploadPath + "/" + fileLinkName;

		String encodingLink = "";

		try {
			encodingLink = URLEncoder.encode(link, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return encodingLink;
	}

	public String getThumbLink() {

			String fileLinkName = sfileName.replace(".", "#");

			String thumbLink = suploadPath + "/s_" + suuid + "_" + fileLinkName;

			String encodingThumbLink = "";

			try {
				encodingThumbLink = URLEncoder.encode(thumbLink, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			return encodingThumbLink;
		
	}
}
