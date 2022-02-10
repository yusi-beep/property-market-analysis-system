package com.real.estate.analizer;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.ImotBgConnector;

@SpringBootTest
public class ExtractDataforOneAdvertTests {

	public static WebDriver driver;
	
	@Test
	public void start() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		Connector extractImotBg = new ImotBgConnector();
		
		driver.manage().window().maximize();
		driver.get(extractImotBg.getWorkPageUrl(driver));
		
	}
	
	
	
}
