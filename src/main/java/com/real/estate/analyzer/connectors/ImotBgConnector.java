package com.real.estate.analyzer.connectors;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.entity.City;
import com.real.estate.analyzer.entity.Neighborhood;
import com.real.estate.analyzer.entity.RealEstateAgency;
import com.real.estate.analyzer.entity.RealEstateType;
import com.real.estate.analyzer.utils.Utils;

public class ImotBgConnector implements Connector {
													
	private static final String WORKPAGE_URL_LINK = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=7ujbrw&f1=1";
	
	private static final String PAGE_URL = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=7ujbrw&f1=";
	
	private static final String COMMA_SEPARATOR = ",";
	
	private static final String CLICK_POPUP_XPATH = "//p [@class='fc-button-label']"; 
	
	private static final String TITLE_XPATH = 
			"//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//strong";
	
	private static final String PRICE_XPATH = "//span[@id='cena']|//td[@class='valgtop']//span";
	
	private static final String FLOOR_XPATH = "//ul[@class='imotData']//li[4]";
	
	private static final String SQUARE_FOOTAGE = "//ul[@class='imotData']//li[2]";
	
	private static final String FULL_ADDRESS_XPATH = 
			"//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//span[1]";
	
	private static final String AGENCY_XPATH = "//a[@class='name']";
	
	//private static final String BROKER_XPATH = "//text()[contains(.,'Брокер:')]";
	
	//private static final String BROKER_PHONE_XPATH = "//text()[contains(.,'Телефон:')]";
	
	//TODO extract extra agency info
	private static final String AGENCY_ADDRESS_XPATH = " ";
	
	private static final String AGENCY_TEL_XPATH = " ";
	
	private static final String AGENCY_CITY_XPATH = " ";
	
	private WebDriver driver;

	public ImotBgConnector() {

		this.driver = Utils.setupWebDriver();
	}
	
	@Override
	public Advert extractData(String url)  {
		
		driver.get(url);
		
		String estateType = Utils.getTextByXpath(driver, TITLE_XPATH);
		
		String fullAddress = Utils.getTextByXpath(driver, FULL_ADDRESS_XPATH);
		
		String[] parts;
		parts = fullAddress.split(COMMA_SEPARATOR);
		
		String neighborhood = parts[1].trim();
		
		String city = parts[0].substring(5);
		
		String priceStr = Utils.getTextByXpath(driver, PRICE_XPATH).replaceAll("\\D+", "");
		Integer price = Utils.parseInteger(priceStr);
		
		String squareFootageStr = Utils.getTextByXpath(driver, SQUARE_FOOTAGE).replaceAll("\\D+", "");
		Integer squareFootage = Utils.parseInteger(squareFootageStr);
		
		String floorStr = Utils.getTextByXpath(driver, FLOOR_XPATH).substring(0, 2).trim().replaceAll("\\D+", "");
		Integer floor = Utils.parseInteger(floorStr);
		
		//TODO cut only broker name
		//String broker = Utils.getTextByXpath(driver, BROKER_XPATH);
		
		//TODO cut only broker telephone
		//String brokerTel = Utils.getTextByXpath(driver, BROKER_PHONE_XPATH);
		
		String agency = Utils.getTextByXpath(driver, AGENCY_XPATH);
		
		LocalDateTime dateTime = LocalDateTime.now();
	
		City tempCity = new City(city);
		
		RealEstateAgency tempAgency;
		
		if (agency != null) {
			
			tempAgency = new RealEstateAgency(agency);
			
		} else {
			
			agency = "Няма агенция";
			tempAgency = new RealEstateAgency(agency);
		}
		RealEstateType tempEstateType = RealEstateType.getTypeFrom(estateType);
		
		Neighborhood tempNeighborhood = new Neighborhood(neighborhood, tempCity);
		
		Advert tempAdvert = new Advert(tempEstateType, squareFootage,
				tempNeighborhood, price, floor, tempAgency, url, dateTime);
		
		System.out.println(tempAdvert);
			
		return tempAdvert;
	}

	@Override
	public HashSet<String> urlSet() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(WORKPAGE_URL_LINK);
		driver.findElement(By.xpath(CLICK_POPUP_XPATH)).click();
		
		HashSet<String> urlSet = new HashSet<String>();
		
		String pagesNumber = Utils.getTextByXpath(driver, "//span[@class='pageNumbersInfo']");
		int lastPage = Integer.parseInt(pagesNumber.substring(pagesNumber.length() - 3).trim());
		
		int page = 1;
	
		while (page != lastPage) {
			
				int beginTableIndex = 6;
				int contentCount = 45;
				
				for (int j = beginTableIndex; j <= contentCount; j++) {
		
					String url = Utils.getLinkXpath(driver, "//table[" + j + "]//a");
					urlSet.add(url);
				}
			
			String nextPage = PAGE_URL + page;
			driver.get(nextPage);
			page++;
		}
		
		return urlSet;
	}
}
