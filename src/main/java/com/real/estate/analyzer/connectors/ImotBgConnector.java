package com.real.estate.analyzer.connectors;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.utils.Utils;

public class ImotBgConnector implements Connector {
	
	private static final String WORKPAGE_URL_LINK = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=7ohjqp&f1=1";
	
	private static final String LAST_PAGE_XPATH = "//a[@class='pageNumbers'][last()]";
	
	private static final String COMMA_SEPARATOR = ",";
	
	private static final String CLICK_POPUP_XPATH = "//p [@class='fc-button-label']"; 
	
	private static final String TITLE_XPATH = "//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//strong";
	
	private static final String PRICE_XPATH = "//span[@id='cena']|//td[@class='valgtop']//span";
	
	private static final String FLOOR_XPATH = "//ul[@class='imotData']//li[4]";
	
	private static final String SQUARE_FOOTAGE = "//ul[@class='imotData']//li[2]";
	
	private static final String FULL_ADDRESS_XPATH = "//div[@style='width:300px; display:inline-block; float:left; margin-top:15px;']//span[1]";
	
	private static final String BROKER_XPATH = "//a[@class='name']";
	
	private WebDriver driver;

	public ImotBgConnector() {

		this.driver = Utils.setupWebDriver();
	}
	
	@Override
	public Advert extractData(String url)  {
		
		driver.get(url);
		
		String title = Utils.getTextByXpath(driver, TITLE_XPATH);
		
		String fullAddress = Utils.getTextByXpath(driver, FULL_ADDRESS_XPATH);
		
		String[] parts;
		parts = fullAddress.split(COMMA_SEPARATOR);
		
		String address = parts[1].trim();
		
		String city = parts[0].substring(5);
		
		String priceStr = Utils.getTextByXpath(driver, PRICE_XPATH).replaceAll("\\D+","");
		int price = Utils.isEmpty(priceStr);
		
		String squareFootageStr = Utils.getTextByXpath(driver, SQUARE_FOOTAGE).replaceAll("\\D+","");
		int squareFootage = Utils.isEmpty(squareFootageStr);
		
		String floorStr = Utils.getTextByXpath(driver, FLOOR_XPATH).substring(0, 2).trim().replaceAll("\\D+","");
		//TODO parter floor or floor don't exist
		int floor = Utils.isEmpty(floorStr);
		
		String broker = Utils.getTextByXpath(driver, BROKER_XPATH);
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		Advert tempAdvert = new Advert(title, squareFootage,
				address, city, price, floor, broker, url, dateTime);
		
		System.out.println(tempAdvert);
			
		return tempAdvert;
	}

	@Override
	public HashSet<String> urlSet() {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(WORKPAGE_URL_LINK);
		Utils.click(driver, CLICK_POPUP_XPATH);
		
		HashSet<String> urlSet = new HashSet<String>();
		
		//TODO create working loop for crawling pages
		String lastPage = Utils.getLinkXpath(driver, "//a[@class='pageNumbers'][last()-2]"); 
		String lastPage1 = WORKPAGE_URL_LINK;
		
		String pageNumbers = Utils.getTextByXpath(driver, "//span[@class='pageNumbersInfo']");
		int pageNum = Integer.parseInt(pageNumbers.substring(pageNumbers.length()-3).trim());
		pageNum = pageNum/10+pageNum%2;
		
		int razdel = 0;
		while(pageNum!=razdel) {
			razdel++;
			int i = 1;
	
			while (!lastPage1.equals(lastPage)) {
				
				int beginTableIndex = 6;
				int contentCount = 10;
				
				for (int j = beginTableIndex; j < contentCount; j++) {
		
					String url = Utils.getLinkXpath(driver, "//table[" + j + "]//a");
					urlSet.add(url);
				}
				lastPage1 = driver.getCurrentUrl();
				Utils.click(driver, "//td[@width='500']/a[" + i + "]");
				
				i++;
				
				log.info("This is lastPage loop");
			}
			
			//i = 2;
			Utils.click(driver, LAST_PAGE_XPATH);
			log.info("This is lastPage2 loop");
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
