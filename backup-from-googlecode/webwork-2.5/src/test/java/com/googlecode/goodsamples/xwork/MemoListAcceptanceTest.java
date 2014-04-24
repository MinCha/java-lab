package com.googlecode.goodsamples.xwork;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jitr.Jitr;
import org.jitr.annotation.BaseUri;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(Jitr.class)
public class MemoListAcceptanceTest extends AbstractDependencyInjectionSpringContextTests {
	@BaseUri
	private String baseURI;
	private WebClient client = new WebClient();
	@Autowired
	MemoRepository memoRepository;

	@Test
	public void shouldDisplayMemoList() throws Exception {
		int previousMemoCount = currentMemoCount();
		addNewMemo();

		int result = currentMemoCount();

		assertThat(result, is(previousMemoCount + 1));
	}

	private void addNewMemo() {
		memoRepository.persist(new Memo("The first memo"));
	}

	private int currentMemoCount() throws IOException, MalformedURLException {
		HtmlPage page = client.getPage(baseURI + "MemoList.nhn");
		HtmlElement result = page.getElementById("memo_count");
		return Integer.parseInt(result.asText());
	}
}