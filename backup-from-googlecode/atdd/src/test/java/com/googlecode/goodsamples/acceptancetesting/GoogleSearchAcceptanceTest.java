package com.googlecode.goodsamples.acceptancetesting;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class GoogleSearchAcceptanceTest {
	@Test
	public void googleShouldProvideValidSearchResult() throws InterruptedException {
		WebDriver driver = new HtmlUnitDriver();
		
		driver.get("http://www.google.com");
		
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("minslovey");
		element.submit();
		
		waitFor(5);
		
		assertThat(driver.getPageSource(), containsString("minslovey.tistory.com"));
	}

	private void waitFor(int seconds) throws InterruptedException {
		Thread.sleep(1000 * seconds);
	}
}
