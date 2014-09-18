package com.github.pagenavigation;

import java.util.ArrayList;
import java.util.List;

class NavigatablePageRange extends BaseObject {
	private int startPage;
	private int endPage;

	NavigatablePageRange(int start, int end) {
		this.startPage = start;
		this.endPage = end;
	}

	int getEndPage() {
		return endPage;
	}

	int getStartPage() {
		return startPage;
	}
	
	List<Page> toPages(PageUrlMaker creator) {
		List<Page> result = new ArrayList<Page>();
		
		for (int i = startPage; i <= endPage; i++) {
			result.add(new Page(i, creator.create(i)));
		}
		
		return result;
		
	}
}
