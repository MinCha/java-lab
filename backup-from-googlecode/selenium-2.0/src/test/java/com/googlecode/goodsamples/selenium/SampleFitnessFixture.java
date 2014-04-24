package com.googlecode.goodsamples.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import fitlibrary.DoFixture;

/**
 * <p>
 * Sample Fitness Fixture
 * </p>
 * <p>
 * <code>
 * !|com.googlecode.goodsamples.selenium.SampleFixture|<br>
 * |open|http://www.google.com|
 * </code>
 * </p>
 */
public class SampleFitnessFixture extends DoFixture {
	public void open(String url) {
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
	}
}
