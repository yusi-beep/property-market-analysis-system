package com.real.estate.analyzer.connectors;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.real.estate.analyzer.entities.Advert;
import com.real.estate.analyzer.entities.City;
import com.real.estate.analyzer.entities.Neighborhood;
import com.real.estate.analyzer.entities.RealEstateAgency;
import com.real.estate.analyzer.enums.RealEstateType;
import com.real.estate.analyzer.utils.Utils;

@Slf4j
public class ImotBgConnector implements Connector {
													
	private static final String WORKPAGE_URL_LINK = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=7ujbrw&f1=1";
	
	private static final String PAGE_URL = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=7ujbrw&f1=";
	
	private static final String COMMA_SEPARATOR = ",";
	
	private static final String CLICK_POPUP_XPATH = "//p [@class='fc-button-label']"; 
	
	private static final String ESTATE_TYPE_XPATH =
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
	
	private static final WebDriver driver = Utils.setupWebDriver();

	@Override
	public Advert extractData(String url)  {

		driver.get(url);
		
		RealEstateType estateType = RealEstateType.getTypeFrom(Utils.getTextByXpath(driver, ESTATE_TYPE_XPATH));
		
		String fullAddress = Utils.getTextByXpath(driver, FULL_ADDRESS_XPATH);
		String[] addressParts;
		addressParts = fullAddress.split(COMMA_SEPARATOR);

		City city = new City();
		city.setName(addressParts[0].substring(5));
		Neighborhood neighbourhood = new Neighborhood();
		neighbourhood.setName(addressParts[1].trim());
		neighbourhood.setCity(city);

		Integer price = Utils.parseInt(Utils.getTextByXpath(driver, PRICE_XPATH));
		Integer squareFootage = Utils.parseInt(Utils.getTextByXpath(driver, SQUARE_FOOTAGE));
		Integer floor = Utils.parseInt(Utils.getTextByXpath(driver, FLOOR_XPATH).substring(0, 2));
		
		//TODO cut only broker name
		//String broker = Utils.getTextByXpath(driver, BROKER_XPATH);
		
		//TODO cut only broker telephone
		//String brokerTel = Utils.getTextByXpath(driver, BROKER_PHONE_XPATH);

		RealEstateAgency agency = new RealEstateAgency();
		agency.setName(Utils.getTextByXpath(driver, AGENCY_XPATH));

		Advert advert = Advert.builder()
				.typeEstate(estateType)
				.squareFootage(squareFootage)
				.neighbourhood(neighbourhood)
				.price(price)
				.floor(floor)
				.agency(agency)
				.url(url)
				.build();
		
		System.out.println(advert);
			
		return advert;
	}

	@Override
	public Set<String> urlSet() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(WORKPAGE_URL_LINK);
		driver.findElement(By.xpath(CLICK_POPUP_XPATH)).click();
		
		Set<String> urlSet = new HashSet<>();
		
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
