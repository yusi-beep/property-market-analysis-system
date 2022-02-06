package com.real.estate.analizer.dtos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.real.estate.analyzer.entity.Advert;

public class ImotBg implements Extract{

	@Override
	public Advert extractData(WebDriver driver, String url) throws InterruptedException {
		
		int delay = 200;
		
		driver.get(url);
	
		Thread.sleep(delay);
		
		
		driver.findElement(By.className("fc-button-label")).click();
		Thread.sleep(delay);
		
		String title = driver.findElement(By
				.xpath("//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//strong"))
				.getText();
		
		String address = driver.findElement(By
				.xpath("//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//span[1]"))
				.getText();
		
		String[] parts;
		
		String splitAt = ",";
		
		parts = splitStringAt(address, splitAt);
		
		address = parts[1]
				.trim();
		
		String city = parts[0]
				.substring(5);
		
		String price = driver.findElement(By
				.xpath("//span[@id='cena']"))
				.getText();
		
		String squareFootage = driver.findElement(By
				.xpath("//ul[@class='imotData']//li[2]"))
				.getText();
		
		String floor = driver.findElement(By
				.xpath("//ul[@class='imotData']//li[4]"))
				.getText()
				.substring(0, 1);
		
		String broker = driver.findElement(By
				.xpath("//a[@class='name']"))
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
