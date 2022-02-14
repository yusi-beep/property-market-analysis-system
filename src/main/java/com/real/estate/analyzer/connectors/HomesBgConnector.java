package com.real.estate.analyzer.connectors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.utils.Utils;

public class HomesBgConnector implements Connector {
	
	private static final String COMMA_SEPARATOR = ",";
	
	private static final String FULL_TITLE_XPATH = "//div[@class='section-title']//h1";
	
	private static final String PRICE_XPATH = "//span[@class='ver20black']";
	
	private static final String FLOOR_XPATH = "//div[@class='Attributes']/div[2]/span/h3";
	
	private static final String FULL_ADDRESS_XPATH = "//div[@class='mdc-layout-grid__inner']//b//h2";
	
	private static final String BROKER_XPATH = "//div[@class='contact-box']//div[1]/b";
	
	private WebDriver driver;
	
	public HomesBgConnector() {
		this.driver = Utils.setupWebDriver();
	}
	
	@Override
	public Advert extractData(String url) {
		
		driver.get(url);
		Utils.sleep(200);
		
		String fullTitle = Utils.getTextByXpath(driver, FULL_TITLE_XPATH);
		String[] parts;
		parts = fullTitle.split(COMMA_SEPARATOR);
		
		String title = parts[0].trim();
		
		String squareFootage = parts[1].trim();
		
		String price = Utils.getTextByXpath(driver, PRICE_XPATH);
		price.replace(',', ' ');
		
		String floor = Utils.getTextByXpath(driver, FLOOR_XPATH).substring(0, 2).trim();
		
		String fullAddress = Utils.getTextByXpath(driver, FULL_ADDRESS_XPATH);
		parts = fullAddress.split(COMMA_SEPARATOR);
		
		String address = parts[0];
		
		String city = parts[1].trim();
		
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
		
		int numberOfUrls = 40;
		
		WebElement url;
		
		for (int i = 0; i < numberOfUrls; i++) {
			
			boolean isEven = i % 2 == 0;
			
			if (i > 4 && isEven) {
				
				url = driver
						.findElement(By
						.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[4]//a"));
			} else if (isEven) {
				
				url = driver
						.findElement(By
						.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[5]//a"));
			} else {
				
				url = driver
					.findElement(By
					.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[" + (i + 1) + "]//a"));
			}
			
			urlArray.add(url.getAttribute("href"));
		
			Utils.scroll(url, driver);
	        Utils.sleep(200);
	        
	        System.out.println(i + "-->" + url.getAttribute("href"));
		}
		
		return urlArray;
	}

	@Override
	public void extractAdverts() {
		
		ArrayList<String> urlHomesBg = (ArrayList<String>) this.urlArray();
		
		for (String url : urlHomesBg) {
			
			this.extractData(url);
			Utils.sleep(200);
		}		
	}

}
