package org.judy.common.util;

import lombok.Data;

@Data
public class PageMaker {

	private int start;
	private int end;
	private boolean prev;
	private boolean next;

	public PageMaker(PageDTO dto, int total) {

		int realEnd = (int) Math.ceil(total / (double) (dto.getPerSheet()));
		
		int tempEnd = (int) Math.ceil(dto.getPage() / 10.0) * 10;

		end = realEnd < tempEnd ? realEnd : tempEnd;

		start = tempEnd - 9;

		prev = start > 1;

		next = realEnd > end ? true : false;

	}

}
