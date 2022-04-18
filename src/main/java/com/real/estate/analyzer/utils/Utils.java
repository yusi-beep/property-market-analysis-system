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

public class Utils {

	private static final Logger log = LoggerFactory.getLogger(Utils.class);
	
	private Utils() {}

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
	
	public static String getTextByXpath(WebDriver driver, String xPath) {
		try {
			return driver.findElement(By.xpath(xPath)).getText();
		} catch (NoSuchElementException e) {
			log.info(String.format("Element with xpath [%s] don't exists", xPath));
			return "";
		} catch (NullPointerException e) {
			return "";
		}
	}
	
	public static String getLinkXpath(WebDriver driver, String xPath) {
		try {
			return  driver.findElement(By.xpath(xPath)).getAttribute("href");
		} catch (NoSuchElementException e) {
			log.info(String.format("Element with xpath [%s] don't exists", xPath));
			return "";
		} catch (NullPointerException e) {
			return "";
		}
	}
	
	public static Integer parseInt(String value) {
		String cleanedUpValue = value.replaceAll("\\D+", "").trim();

		try {
			return Integer.parseInt(cleanedUpValue);
		} catch (NumberFormatException e) {
			log.info(String.format("%s is not a number", cleanedUpValue));
			return 0;
		} catch (StringIndexOutOfBoundsException e) {
			log.info(String.format("String index out of bound: %s", cleanedUpValue));
			return 0;
		} catch (NullPointerException e) {
			log.info(String.format("Null pointer at string: %s", cleanedUpValue));
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

	public static String  isExists(String string) {
		
		if (string != null) {
			return string;
		}
		
		return "Няма информация";
	}
	/*
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
	*/
}