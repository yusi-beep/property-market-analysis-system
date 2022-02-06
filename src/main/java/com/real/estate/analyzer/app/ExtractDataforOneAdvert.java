package com.real.estate.analyzer.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.real.estate.analizer.dtos.Extract;
import com.real.estate.analizer.dtos.HomesBg;
import com.real.estate.analizer.dtos.ImotBg;

public class ExtractDataforOneAdvert {

	public static void main(String[] args)throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", 
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		String urlImotBg = "https://www.imot.bg/pcgi/imot.cgi?act=5&adv=1b164382325993161";
		
		Extract extractImotBg = new ImotBg();
		extractImotBg.extractData(driver, urlImotBg);
		
		System.out.println("<---------------------------->");
		
		String urlHomesBg = "https://www.homes.bg/offer/apartament-za-prodazhba/tristaen-89m2-sofiya-zhk.-ljulin-6/as1370158";
		
		Extract extractHomesBg = new HomesBg();
		extractHomesBg.extractData(driver, urlHomesBg);
		
		driver.close();
		}
}
