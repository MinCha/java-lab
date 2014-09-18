package com.github.pagenavigation;

import java.util.List;

public class PageNavigation {
	static final String FIXED_PAGE_N_EXPRESSION_RULE = "{pageN}";
	final String pageExpressionPattern;
	private int pageCountToDisplay = 10;
	private int totalItemCount;
	private int itemCountPerPage;
	private int currentPage;
	private PageUrlMaker urlMaker;

	public PageNavigation(int totalItemCount, int itemCountPerPage,
			int currentPage, String path, String queryString) {
		this(totalItemCount, itemCountPerPage, currentPage, path, queryString,
				"page=" + FIXED_PAGE_N_EXPRESSION_RULE);
	}

	public PageNavigation(int totalItemCount, int itemCountPerPage,
			int currentPage, String path, String queryString,
			String pageExpressionPattern) {
		if (pageExpressionPattern.contains(FIXED_PAGE_N_EXPRESSION_RULE) == false) {
			throw new IllegalArgumentException(
					"Wrong page expression. An right expression : 'page="
							+ FIXED_PAGE_N_EXPRESSION_RULE
							+ "' Passed wrong expression : "
							+ pageExpressionPattern);
		}

		this.totalItemCount = totalItemCount;
		this.itemCountPerPage = itemCountPerPage;
		this.currentPage = currentPage;
		this.urlMaker = new PageUrlMaker(path, queryString,
				pageExpressionPattern);
		this.pageExpressionPattern = pageExpressionPattern;
	}

	public String getFirstPageUrl() {
		return urlMaker.create(1);
	}

	public String getLastPageUrl() {
		return urlMaker.create(computeLastPage());
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public boolean isPreviousNavigatablePages() {
		return getPageRange().getStartPage() > pageCountToDisplay;
	}

	public boolean isNextNavigatablePages() {
		return computeLastPage() > getPageRange().getEndPage();
	}

	public String getFirstUrlOfPreviousNavigatablePages() {
		int page = getPageRangeOf(pageCountToDisplay,
				currentPage - pageCountToDisplay).getStartPage();
		return urlMaker.create(page);
	}

	public String getFirstUrlOfNextNavigatablePages() {
		int page = getPageRangeOf(pageCountToDisplay,
				currentPage + pageCountToDisplay).getStartPage();
		return urlMaker.create(page);
	}

	public List<Page> getNavigatablePages() {
		NavigatablePageRange pageRange = getPageRangeOf(pageCountToDisplay,
				currentPage);
		return pageRange.toPages(urlMaker);
	}

	private boolean avaliblePage(int page) {
		return computeLastPage() >= page;
	}

	private int computeLastPage() {
		return totalItemCount % itemCountPerPage == 0 ? totalItemCount
				/ itemCountPerPage : (totalItemCount / itemCountPerPage) + 1;
	}

	private NavigatablePageRange getPageRangeOf(int pageCountToDisplay,
			int passedCurrentPage) {
		int position = passedCurrentPage % pageCountToDisplay == 0 ? (passedCurrentPage / pageCountToDisplay) - 1
				: passedCurrentPage / pageCountToDisplay;
		int startPage = (position * pageCountToDisplay) + 1;
		int endPage = startPage + (pageCountToDisplay - 1);

		if (avaliblePage(endPage) == false) {
			endPage = computeLastPage();
		}

		return new NavigatablePageRange(startPage, endPage);
	}

	private NavigatablePageRange getPageRange() {
		return getPageRangeOf(pageCountToDisplay, currentPage);
	}
}
