package com.github.pagenavigation;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.github.pagenavigation.PageUrlMaker;

public class PageUrlMakerTest {
	private PageUrlMaker sut;
	
	@Test
	public void shouldKeepQueryString() {
		sut = new PageUrlMaker("/list.nhn" ,"page=1&sort=desc", "page={pageN}");
		
		String url = sut.create(5);
		
		assertThat(url, is("/list.nhn?page=5&sort=desc"));
	}

	@Test
	public void shouldSupportRestfulStyle() {
		sut = new PageUrlMaker("/list/1/like" ,"sort=desc", "/list/{pageN}/like");
		
		String url = sut.create(5);
		
		assertThat(url, is("/list/5/like?sort=desc"));
	}
	
	@Test
	public void shouldEncodeQueryStringAsUTF8() throws UnsupportedEncodingException {
		sut = new PageUrlMaker("/list/1/like" ,"lang=한국어", "/list/{pageN}/like");
		
		String url = sut.create(999);

		assertThat(url, is("/list/999/like?lang=" + URLEncoder.encode("한국어","UTF-8")));
	}

}
