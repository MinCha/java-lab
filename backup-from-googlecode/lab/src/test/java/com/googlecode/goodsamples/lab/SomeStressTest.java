package com.googlecode.goodsamples.lab;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SomeStressTest {
	@Test
	public void stress() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient client = new WebClient();
		long serverLatency = 0;
		for (int i = 0; i < 100; i++) {
			HtmlPage result = client.getPage("http://localhost:8080/lab/blog/minslovey");
			assertThat(result.getWebResponse().getStatusCode(), is(200));
			serverLatency = serverLatency + result.getWebResponse().getLoadTime();
		}
		System.out.println(serverLatency);
		
		serverLatency = 0;
		for (int i = 0; i < 100; i++) {
			HtmlPage result = client.getPage("http://localhost:8080/index.html");
			assertThat(result.getWebResponse().getStatusCode(), is(200));
			serverLatency = serverLatency + result.getWebResponse().getLoadTime();
		}
		System.out.println(serverLatency);
	}
}
