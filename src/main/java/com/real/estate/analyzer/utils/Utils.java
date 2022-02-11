package com.real.estate.analyzer.utils;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.HomesBgConnector;
import com.real.estate.analyzer.connectors.ImotBgConnector;


public class Utils {

	private static Connector connector;
	
	private static WebDriver driver;
	
	public static void sleep(int time)  {
		
		try {
			
			Thread.sleep(time);
			
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	public static void scroll(WebElement url) {
		
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", url );
	}
	
	public static WebDriver setupWebDriver() {
		
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);//for testing
		
		return new ChromeDriver(options);
	}
	
	public static void extractAdvertsHomesBg() {
		
		driver = setupWebDriver();
		
		connector = new HomesBgConnector();
		
		driver.get("https://www.homes.bg/");
		
		ArrayList<String> urlHomesBg = (ArrayList<String>) connector.urlArray(driver);
		
		for (String url : urlHomesBg) {
			
			connector.extractData(driver, url);
			Utils.sleep(200);
		}
	}
	
	public static void extractAdvertsImotBg() {
		
		driver = setupWebDriver();
		
		connector = new ImotBgConnector();
		
		driver.get("https://www.imot.bg/pcgi/imot.cgi?act=3&slink=7o0gd5&f1=1");
		driver.findElement(By.className("fc-button-label")).click();
		Utils.sleep(200);
		
		ArrayList<String> urlImotBg = (ArrayList<String>) connector.urlArray(driver);
		
		for (String url : urlImotBg) {
			
			connector.extractData(driver, url);
		}
		driver.close();
	}
}
