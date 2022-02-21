package com.real.estate.analyzer.connectors;

import java.time.LocalDateTime;
import java.util.HashSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.utils.Utils;

public class HomesBgConnector implements Connector {
	
	private static final String WORKPAGE_URL_LINK = "https://www.homes.bg/?currencyId=1&filterOrderBy=0&locationId=0&typeId=ApartmentSell";
	
	private static final String COMMA_SEPARATOR = ",";
	
	private static final String FULL_TITLE_XPATH = "//div[@class='section-title']//h1";
	
	private static final String TOTAL_ADVERTS = "//span[@class='milestone-total']";
	
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
		
		String fullTitle = Utils.getTextByXpath(driver, FULL_TITLE_XPATH);
		String[] parts;
		parts = fullTitle.split(COMMA_SEPARATOR);
		
		String title = parts[0].trim();
		
		String squareFootageStr = parts[1].trim().replaceAll("\\D+","");
		int squareFootage = Utils.isEmpty(squareFootageStr);
		
		String priceStr = Utils.getTextByXpath(driver, PRICE_XPATH).replaceAll("\\D+","");
		int price = Utils.isEmpty(priceStr);
		
		String floorStr = Utils.getTextByXpath(driver, FLOOR_XPATH).substring(0, 1).trim().replaceAll("\\D+","");
		int floor = Utils.isEmpty(floorStr);
		
		String fullAddress = Utils.getTextByXpath(driver, FULL_ADDRESS_XPATH);
		parts = fullAddress.split(COMMA_SEPARATOR);
		
		String address = parts[0].trim();
		
		String city = parts[1].trim();
		
		String broker = Utils.getTextByXpath(driver, BROKER_XPATH);
		
		LocalDateTime dateTime = LocalDateTime.now();

		Advert tempAdvert = new Advert(title, squareFootage,
				address, city, price, floor, broker, url, dateTime);
		
		System.out.println(tempAdvert);
		
		return tempAdvert;
	}

	@Override
	public HashSet<String> urlSet() {
		
		driver.get(WORKPAGE_URL_LINK);
		
		HashSet<String> urlSet = new HashSet<String>();
		/*
		Utils.sleep(2000);
		String totalAdverts = Utils.getTextByXpath(driver, TOTAL_ADVERTS);
		int numberOfUrls = Integer.parseInt(totalAdverts);
		*/
		WebElement url;
		
		for (int i = 1; i < /*numberOfUrls*/20; i++) {
			
			boolean isEven = i % 2 == 0;
			
			if (i > 3 && isEven) {
				
				url = driver
						.findElement(By
						.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[4]//a"));
				
			} else if (i < 3) {
				
				url = driver
					.findElement(By
					.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[" + i + "]//a"));
				
			} else {
				
				url = driver
						.findElement(By
						.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[3]//a"));
		        }
			
			Utils.scroll(url, driver);
	        Utils.sleep(2000);
			urlSet.add(url.getAttribute("href"));
		
			System.out.println(i + "-->" + url.getAttribute("href"));
	       
			}
		
		return urlSet;
	}
	
	//TODO Add repository saving function
	@Override
	public void extractAdverts() {
		
		HashSet<String> urlLinks = (HashSet<String>) this.urlSet();
		
		for (String url : urlLinks) {
			
			extractData(url);
			Utils.sleep(2000);
		}	
	}
}