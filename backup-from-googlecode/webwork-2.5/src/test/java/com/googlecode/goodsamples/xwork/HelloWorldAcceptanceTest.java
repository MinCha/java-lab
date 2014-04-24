package com.googlecode.goodsamples.xwork;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.jitr.Jitr;
import org.jitr.annotation.BaseUri;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


@RunWith(Jitr.class)
public class HelloWorldAcceptanceTest {
    @BaseUri
    private String baseURI;
    private WebClient client = new WebClient();
    
	@Test
	public void shouldDisplayHello() throws Exception {
		HtmlPage page = client.getPage(baseURI + "Hello.nhn");
		HtmlElement element = page.getElementById("message");
		
		assertThat(element.asText(), containsString("Hello"));
	}
}
