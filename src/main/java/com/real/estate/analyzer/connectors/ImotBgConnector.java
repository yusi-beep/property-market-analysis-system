package com.real.estate.analyzer.connectors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.utils.Utils;

public class ImotBgConnector implements Connector {

	private final String COMMA_SEPARATOR = ",";
	
	private final String TITLE_XPATH = "//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//strong";
	
	private final String PRICE_XPATH = "//span[@id='cena']|//td[@class='valgtop']//span";
	
	private final String FLOOR_XPATH = "//ul[@class='imotData']//li[4]";
	
	private final String SQUARE_FOOTAGE = "//ul[@class='imotData']//li[2]";
	
	private final String FULL_ADDRESS_XPATH = "//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//span[1]";
	
	private final String BROKER_XPATH = "//a[@class='name']";
	
	private WebDriver driver;

	@Override
	public Advert extractData(String url)  {
		
		int delay = 200;
		
		driver.get(url);
	
		Utils.sleep(delay);
		
		String title = Utils.getTextByXpath(driver, TITLE_XPATH);
		
		String fullAddress = Utils.getTextByXpath(driver, FULL_ADDRESS_XPATH);
		
		String[] parts;
		
		parts = fullAddress.split(COMMA_SEPARATOR);
		
		String address = parts[1]
				.trim();
		
		String city = parts[0]
				.substring(5);
		
		String price = Utils.getTextByXpath(driver, PRICE_XPATH);
		
		String squareFootage = Utils.getTextByXpath(driver, SQUARE_FOOTAGE);
		
		String floor = Utils.getTextByXpath(driver, FLOOR_XPATH).substring(0, 2).trim();
		
		String broker = Utils.getTextByXpath(driver, BROKER_XPATH);
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		
		Advert tempAdvert = new Advert(title, squareFootage,
				price, floor, address, city, broker, url, dateTime);
			
			System.out.println(tempAdvert);
			
			return tempAdvert;
	}

	@Override
	public List<String> urlArray() {
		
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
	public void extractAdverts() {
		// TODO Auto-generated method stub
		
	}
}
