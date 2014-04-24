package com.googlecode.goodsamples.selenium;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GoogleSearchAcceptanceTest {
	WebDriver driver;
	Navigation navigation;
	
	@Test
	public void shouldContainExpectedKeyword() throws Exception {
        moveTo("http://google.com");
        typeOnSearchBox("google");
        clickSearchButton();

        assertThat(driver.getPageSource(), containsString("google"));
	}

	@Before
	public void openBrowser() {
		driver = new FirefoxDriver();
		navigation = driver.navigate();
	}

	@After
	public void closeBrowser() {
        driver.close();		
	}
	
	private void clickSearchButton() {
		WebElement searchButton = driver.findElement(By.name("btnG"));
        searchButton.submit();
        thinkDuring(2);
	}

	private void typeOnSearchBox(String keyword) {
		WebElement queryBox = driver.findElement(By.id("q"));
        queryBox.sendKeys(keyword);
        thinkDuring(2);
	}

	private void moveTo(String url) {
		driver.get(url);
        thinkDuring(2);
	}
	
	private void thinkDuring(int second) {
        try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
		}		
	}
}
