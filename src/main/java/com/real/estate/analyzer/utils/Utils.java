package com.real.estate.analyzer.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.real.estate.analyzer.repository.AdvertRepository;

public class Utils {
	
	private static final Logger log = LoggerFactory.getLogger(Utils.class);
	private static AdvertRepository advertRepository;
	
	public static void sleep(int time)  {
		
		try {
			
			Thread.sleep(time);
			
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	public static void scroll(WebElement element, WebDriver driver) {
		
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		
	}
	
	public static WebDriver setupWebDriver() {
		
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("enable-automation");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-extensions");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--disable-gpu");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		options.setHeadless(true);
		
		return new ChromeDriver(options);
	}
	
	public static String getTextByXpath(WebDriver driver, String xPath) {
		 	
		try {
			
			String element = driver.findElement(By.xpath(xPath)).getText();
				
			return element;
			
		} catch (NoSuchElementException e) {
			
			log.info("Element don't exists with xpath [" + xPath + "]");
			
			return "";
		
		} catch (NullPointerException e) {
			
			return "";
		}
	}
	
	public static String getLinkXpath(WebDriver driver, String xPath) {
		
		try {
			
			WebElement element = driver.findElement(By.xpath(xPath));
		
			return element.getAttribute("href");
		
		} catch (NoSuchElementException e) {
			
			log.info("Element don't exists with xpath [" + xPath + "]");
			
			return "";
		}
	}
	
	public static Integer parseInteger(String string) {
		
		try {
			
			Integer value = Integer.parseInt(string);
			
			return value;
			
		} catch (NumberFormatException e) {
			
			log.info("Number format at string: " + string);
			
			return null;
		}
	}
	
	public static Integer parseInt(String string) {
		
		try {
			
			int intValue = Integer.parseInt(string.substring(0, 3).replaceAll("\\D+", ""));
			
			return intValue;
			
		} catch (NumberFormatException e) {
			
			log.info("Number format at string: " + string);
			
			return 0;
			
		} catch (StringIndexOutOfBoundsException e) {
			
			log.info("String index out of bound:" + string);
			
			return null;
		
		} catch (NullPointerException e) {
			
			log.info("Null pointer at string: " + string);
			
			return 0;
		}
	}
	
public static Integer parseIntTotal(String string) {
		
		try {
		
			int intValue = Integer.parseInt(string);
			
			return intValue;
			
		} catch (NumberFormatException e) {
			
			log.info("Number format at string: " + string);
			
			return 0;
		
		} catch (NullPointerException e) {
	
			log.info("Null pointer at string: " + string);
	
			return 0;
		}
	}

	public static String[] split(String splitAt, String string) {
	
		String[] parts = new String[2];
	
		if (string.contains(",")) {
	
			parts = string.split(splitAt);
	
			return parts;
	
		} else {
	
			parts[0] = "";
			parts[1] = "";
	
			return parts;
		}
	}
	
	public static boolean isDuplicate(String address, 
			String city, Integer floor, Integer squareFootage, String broker) {
		
		try {
			
			boolean isDuplicates = advertRepository.checkForDuplicates(
					address, city, floor, squareFootage, broker) == null;
			 
			 return isDuplicates;
			 
		} catch (org.springframework.dao.IncorrectResultSizeDataAccessException e ) {
			return false;
			
		} catch (NullPointerException e) {
			return true;
		}
	}
}