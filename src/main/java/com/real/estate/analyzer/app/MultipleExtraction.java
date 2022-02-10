package com.real.estate.analyzer.app;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.HomesBgConnector;
import com.real.estate.analyzer.connectors.ImotBgConnector;

public class MultipleExtraction {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		Connector extractHomesBg = new HomesBgConnector();
		
		driver.get(extractHomesBg.getWorkPageUrl(driver));
		
		ArrayList<String> urlHomesBg = (ArrayList<String>) extractHomesBg.urlArray(driver);
		
		for (String url : urlHomesBg) {
			
			extractHomesBg.extractData(driver, url);
			Thread.sleep(200);
		}
		
		System.out.println("<---------------------------->");
		
		Connector extractImotBg = new ImotBgConnector();
		
		driver.get(extractImotBg.getWorkPageUrl(driver));
		
		ArrayList<String> urlImotBg = (ArrayList<String>) extractImotBg.urlArray(driver);
		
		for (String url : urlImotBg) {
			
			extractImotBg.extractData(driver, url);
		}
		
		driver.close();
	}

}
