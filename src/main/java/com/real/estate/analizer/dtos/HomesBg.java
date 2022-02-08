package com.real.estate.analizer.dtos;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.real.estate.analyzer.entity.Advert;

public class HomesBg implements Extract {
	
	@Override
	public Advert extractData(WebDriver driver, String urlHomesBg) throws InterruptedException {
		
		String[] parts;
		
		String splitAt = ",";
		
		driver.get(urlHomesBg);
		Thread.sleep(200);
		
		String title = driver.findElement(By
				.xpath("//div[@class='section-title']//h1"))
				.getText();
		
		parts = splitStringAt(title, splitAt);
		
		title = parts[0];
		
		String squareFootage = parts[1].trim();
		
		String price = driver.findElement(By.xpath("//span[@class='ver20black']"))
				.getText();
		
		parts = splitStringAt(price, splitAt);
		price = parts[0] + " " + parts[1];
		 
		 String floor = driver.findElement(By
				.xpath("//div[@class='Attributes']/div[2]/span/h3"))
				.getText()
				.substring(0, 1);
		
		String address = driver.findElement(By
				.xpath("//div[@class='mdc-layout-grid__inner']//b//h2"))
				.getText();
		
		parts = splitStringAt(address, splitAt);
		
		address = parts[0];
		
		String city = parts[1]
				.trim();
		
		String broker = driver.findElement(By
				.xpath("//div[@class='contact-box']//div[1]/b"))
				.getText();
		
		Advert tempAdvert = new Advert(
				title, squareFootage, address, city, price, floor, broker);

		System.out.println(tempAdvert.toString());
		
		return tempAdvert;
	}
	
	public static String[] splitStringAt(String str, String splitAt) {
		
		String[] parts = str.split(splitAt);
		
		return parts;
	}

	@Override
	public List<String> urlArray(WebDriver driver) throws InterruptedException {
		
		List<String> urlArray = new ArrayList<String>();
		
		int numberOfUrls = 40;
		
		WebElement url;
		
		for (int i = 0; i < numberOfUrls; i++) {
			
			boolean evenOrOdd = (i % 2 == 0);
			
			if (i > 4 && evenOrOdd == true) {
				
				url = driver
						.findElement(By
						.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[4]//a"));
			} else if (evenOrOdd == false) {
				
				url = driver
						.findElement(By
						.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[5]//a"));
			} else {
				
				url = driver
					.findElement(By
					.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[" + (i + 1) + "]//a"));
			}
			
			urlArray.add(url.getAttribute("href"));
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
	        jse.executeScript("arguments[0].scrollIntoView();", url );
	        Thread.sleep(200);
	        
	        System.out.println(i + "-->" + url.getAttribute("href"));
		}
		
		return urlArray;
	}

	@Override
	public String getWorkPageUrl(WebDriver driver) throws InterruptedException {
		
		driver.manage().window().maximize();
		String url = "https://www.homes.bg/";
		Thread.sleep(100);
		
		return url;
	}
	
}
