package com.real.estate.analyzer.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	
	private static final Logger log = LoggerFactory.getLogger(Utils.class);
	
	public static void sleep(int time)  {
		
		try {
			
			Thread.sleep(time);
			
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	public static void scroll(WebElement element, WebDriver driver) {
		
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element );
		
	}
	
	public static WebDriver setupWebDriver() {
		
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		
		return new ChromeDriver(options);
	}
	
	//check xpath contains
	public static String getTextByXpath(WebDriver driver, String xPath) {

		try {
			
			WebElement element = driver.findElement(By.xpath(xPath));
			return element.getText();
			
		} catch (Exception e) {
			log.info("Element exists with xpath [" + xPath + "]");
			return "";
		}
	}
	
	public static String getLinkXpath(WebDriver driver, String xPath) {

		WebElement element = driver.findElement(By.xpath(xPath));
			
		if (element != null) {
			return element.getAttribute("href");
		}
			
		log.info("Element exists with xpath [" + xPath + "]");
		
		return "";
	}
	
	public static void click(WebDriver driver, String xPath) {
		
		try {
			
			WebElement element = driver.findElement(By.xpath(xPath));
			element.click();
		
		} catch (Exception e){
			
			e.printStackTrace();
			log.info("This is stale element exeption in >>>>>>>>"+ xPath+"<<<<<");
		}
	}
	
	public static int isEmpty(String string) {
		
		if(!string.isEmpty()) {
			
			int value = Integer.parseInt(string);
			return value;
		}
		
		return 0;
	}
}
