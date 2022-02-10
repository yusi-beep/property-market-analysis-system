package com.real.estate.analyzer.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.HomesBgConnector;
import com.real.estate.analyzer.connectors.ImotBgConnector;

public class ExtractDataforOneAdvert {

	public static void main(String[] args)throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", 
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		String urlImotBg = "https://www.imot.bg/pcgi/imot.cgi?act=5&adv=1b164382325993161";
		
		Connector extractImotBg = new ImotBgConnector();
		extractImotBg.extractData(driver, urlImotBg);
		
		System.out.println("<---------------------------->");
		
		String urlHomesBg = "https://www.homes.bg/offer/apartament-za-prodazhba/tristaen-89m2-sofiya-zhk.-ljulin-6/as1370158";
		
		Connector extractHomesBg = new HomesBgConnector();
		extractHomesBg.extractData(driver, urlHomesBg);
		
		driver.close();
		}
}
