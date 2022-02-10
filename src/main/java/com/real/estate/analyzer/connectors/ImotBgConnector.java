package com.real.estate.analyzer.connectors;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.real.estate.analyzer.entity.Advert;

public class ImotBgConnector implements Connector {

	private final String COMMA_SEPARATOR = ",";
	
	
	@Override
	public Advert extractData(WebDriver driver, String url) throws InterruptedException {
		
		int delay = 200;
		
		driver.get(url);
	
		Thread.sleep(delay);
		
		String title = driver.findElement(By
				.xpath("//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//strong"))
				.getText();
		
		String address = driver.findElement(By
				.xpath("//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//span[1]"))
				.getText();
		
		String[] parts;
		
		parts = address.split(COMMA_SEPARATOR);
		
		address = parts[1]
				.trim();
		
		String city = parts[0]
				.substring(5);
		
		String price = driver.findElement(By
				.xpath("//span[@id='cena']|//td[@class='valgtop']//span"))
				.getText();
		
		String squareFootage = driver.findElement(By
				.xpath("//ul[@class='imotData']//li[2]"))
				.getText();
		
		String floor = driver.findElement(By
				.xpath("//ul[@class='imotData']//li[4]"))
				.getText()
				.substring(0, 2)
				.trim();
		
		String broker = driver.findElement(By
				.xpath("//a[@class='name']|//div[@class='AG']//strong"))
				.getText();
		
			Advert tempAdvert = new Advert(
					title, squareFootage, address, city, price, floor, broker);
		
			System.out.println(tempAdvert);
			
			return tempAdvert;
	}

	@Override
	public List<String> urlArray(WebDriver driver) {
		
		List<String> urlArray = new ArrayList<String>();
		
		int beginTableIndex = 6;
		int contentCount = 45;
		
		int lastPageIndex = 2;
		
		for (int i = 0; i < lastPageIndex; i++) {
			
			for (int j = beginTableIndex; j < contentCount; j++) {
				
				String url = driver
						.findElement(By.xpath("//table[" + j + "]//a"))
						.getAttribute("href")
						.toString();
				
				 urlArray.add(url);
			}
		
			
		driver.findElement(By.xpath("//td[@width='500']//a[" + (i + 1) + "]")).click();
		}
		
		return urlArray;
	}

	@Override
	public String getWorkPageUrl(WebDriver driver) throws InterruptedException {
		
		int delay = 100;
		
		driver.manage().window().maximize();
		
		driver.get("https://www.imot.bg/pcgi/imot.cgi?act=3&slink=7o0gd5&f1=1");
		
		driver.findElement(By.className("fc-button-label")).click();
		Thread.sleep(delay);
		
		String url = driver.getCurrentUrl();
		
		return url;
	}

}
