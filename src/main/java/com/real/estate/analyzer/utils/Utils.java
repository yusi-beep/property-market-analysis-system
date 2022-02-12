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
	
	public static void sleep(int time)  {
		
		try {
			
			Thread.sleep(time);
			
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	public static void scroll(WebElement url, WebDriver driver) {
		
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", url );
	}
	
	public static WebDriver setupWebDriver() {
		
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);//for testing
		
		return new ChromeDriver(options);
	}
	
	//check xpath contains
	public static String getTextByXpath(WebDriver driver, String xPath){

			WebElement element = driver.findElement(By.xpath(xPath));
			
			if(element != null){
				return element.getText();
			}
			//TODO add logger 
			return "";
		}
	
}
