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
	
	private static final String GET_ELEMENT = "//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[4]//a";
	
	private static final String FULL_TITLE_XPATH = "//div[@class='section-title']//h1";
	
	private static final String TOTAL_ADVERTS = "//span[@class='milestone-total']";
	
	private static final String PRICE_XPATH = "//span[@class='ver20black']";
	
	private static final String FLOOR_XPATH = "//div[@class='item']//text()[contains(.,'Етаж')]/following::h3";
	
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
		parts = Utils.split(COMMA_SEPARATOR, fullTitle);	
		
		String title = parts[0].trim();
			
		String squareFootageStr = parts[1].trim().replaceAll("\\D+", "");
		
		Integer squareFootage = Utils.parseInteger(squareFootageStr);
		
		String priceStr = Utils.getTextByXpath(driver, PRICE_XPATH).replaceAll("\\D+", "");
		Integer price = Utils.parseInteger(priceStr);

		String floorStr = Utils.getTextByXpath(driver, FLOOR_XPATH);
		Integer floor = Utils.parseInt(floorStr);
		
		String fullAddress = Utils.getTextByXpath(driver, FULL_ADDRESS_XPATH);
		parts = Utils.split(COMMA_SEPARATOR, fullAddress);
		
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
		
		WebElement getElement = driver.findElement(By.xpath(GET_ELEMENT));
		
		Utils.scroll(getElement, driver);
		
		Utils.sleep(500);
		String totalAdverts = Utils.getTextByXpath(driver, TOTAL_ADVERTS);
		
		int	numberOfUrls = Utils.parseIntTotal(totalAdverts);
		
		log.info("" + numberOfUrls);
		
		String getIdStr = getElement.getAttribute("href");
		int getId = Integer.parseInt(getIdStr.substring(getIdStr.length() - 7));
		
		for (int i = 1; i < numberOfUrls; i++) {
			
			String url = "https://www.homes.bg//as" + getId;
			urlSet.add(url);
			getId--;
		
			}
		
		return urlSet;
	}
}