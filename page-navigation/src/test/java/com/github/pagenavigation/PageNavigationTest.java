package com.github.pagenavigation;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.pagenavigation.Page;
import com.github.pagenavigation.PageNavigation;

@RunWith(MockitoJUnitRunner.class)
public class PageNavigationTest {
	private PageNavigation sut;
	private final int totalItemCount = 524;
	private final int itemCountPerPage = 20;
	private final int currentPage = 1;
	private final String path = "/list";
	private final String qs = "page=1&sort=title&sortType=desc";
	private final String onlyPageQs = "page=1";
	
	@Test
	public void canMakeFirstPageUrl() {
		sut = new PageNavigation(totalItemCount, itemCountPerPage, currentPage, path, qs);

		String result = sut.getFirstPageUrl();

		assertThat(result, is("/list?page=1&sort=title&sortType=desc"));
	}

	@Test
	public void canMakeLastPageUrl() {
		sut = new PageNavigation(totalItemCount, itemCountPerPage, currentPage, path, qs);

		String result = sut.getLastPageUrl();

		assertThat(result, is("/list?page=27&sort=title&sortType=desc"));
	}

	@Test
	public void canComputeNavigatablePageRange() {
		final String queryString = "page=1";
		assertThat(new PageNavigation(115, 20, 2, path, queryString).getNavigatablePages(), is(pagesBetween(1, 6)));
		assertThat(new PageNavigation(115, 20, 7, path, queryString).getNavigatablePages(), is(pagesBetween(1, 6)));
		assertThat(new PageNavigation(515, 20, 10, path, queryString).getNavigatablePages(), is(pagesBetween(1, 10)));
		assertThat(new PageNavigation(215, 20, 11, path, queryString).getNavigatablePages(), is(pagesBetween(11, 11)));
		assertThat(new PageNavigation(11115, 20, 55, path, queryString).getNavigatablePages(), is(pagesBetween(51, 60)));
	}

	@Test
	public void canKnowThereIsNextNavigatablePages() {
		assertThat(new PageNavigation(115, 20, 2, path, qs).isNextNavigatablePages(), is(false));
		assertThat(new PageNavigation(215, 20, 2, path, qs).isNextNavigatablePages(), is(true));
		assertThat(new PageNavigation(11115, 20, 55, path, qs).isNextNavigatablePages(), is(true));
	}

	@Test
	public void canKnowThrerIsPreviousNavigatablePages() {
		assertThat(new PageNavigation(215, 20, 1, path, qs).isPreviousNavigatablePages(), is(false));
		assertThat(new PageNavigation(215, 20, 11, path, qs).isPreviousNavigatablePages(), is(true));
	}

	@Test
	public void canComputeFirstPageUrlOfPreviousNavigatablePages() {
		assertThat(new PageNavigation(215, 20, 11, path, onlyPageQs).getFirstUrlOfPreviousNavigatablePages(), is("/list?page=1"));
		assertThat(new PageNavigation(11115, 20, 55, path, onlyPageQs).getFirstUrlOfPreviousNavigatablePages(), is("/list?page=41"));
	}

	@Test
	public void canComputeFirstPageUrlOfNextNavigatablePages() {
		assertThat(new PageNavigation(215, 20, 5, path, onlyPageQs).getFirstUrlOfNextNavigatablePages(), is("/list?page=11"));
		assertThat(new PageNavigation(11115, 20, 55, path, onlyPageQs).getFirstUrlOfNextNavigatablePages(), is("/list?page=61"));
	}

	@Test
	public void canCustomizePageExpressionPattern() {
		final String userCustomizedPattern = "/list/" + PageNavigation.FIXED_PAGE_N_EXPRESSION_RULE;
		PageNavigation sut = new PageNavigation(100, 20, 3, "/list/3", "", userCustomizedPattern);
		
		assertThat(sut.getFirstPageUrl(), is("/list/1"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowException_IfCustomizedPageExpressionIsInvalid() {
		String wrong = "myPage";
		new PageNavigation(100, 20, 1, path, qs, "/list/" + wrong);
	}

	private List<Page> pagesBetween(int start, int end) {
		List<Page> result = new ArrayList<Page>();
		
		for (int i = start; i <= end; i++) {
			result.add(new Page(i, path + "?page=" + i));
		}
		
		return result;
	}
}
