package com.github.pagenavigation;

public class Page extends BaseObject {
	private int pageNumber;
	private String url;

	public Page(int pageNumber, String pageUrl) {
		this.pageNumber = pageNumber;
		this.url = pageUrl;
	}

	public int getPageNumber() {
		return pageNumber;
	}
	
	public String getUrl() {
		return url;
	}
}