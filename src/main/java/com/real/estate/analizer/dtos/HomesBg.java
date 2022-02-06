package com.real.estate.analizer.dtos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.real.estate.analyzer.entity.Advert;

public class HomesBg implements Extract{
	
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
				.xpath("//div[2]/span/h3"))
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

		return tempAdvert;
	}
	
	public static String[] splitStringAt(String str, String splitAt) {
		
		String[] parts = str.split(splitAt);
		
		return parts;
	}
}
