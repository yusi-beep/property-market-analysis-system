package com.real.estate.analyzer.connectors;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.real.estate.analyzer.entities.Advert;
import com.real.estate.analyzer.entities.City;
import com.real.estate.analyzer.entities.Neighbourhood;
import com.real.estate.analyzer.entities.RealEstateAgency;
import com.real.estate.analyzer.enums.RealEstateType;
import com.real.estate.analyzer.repository.AgencyRepository;
import com.real.estate.analyzer.repository.CityRepository;
import com.real.estate.analyzer.repository.NeighbourhoodRepository;
import com.real.estate.analyzer.utils.Utils;

@Slf4j
public class ImotBgConnector implements Connector {
													
	private static final String WORKPAGE_URL_LINK = "https://www.imot.bg/pcgi/imot.cgi";
	
	private static final String PAGE_URL = "https://www.imot.bg/pcgi/imot.cgi?act=3&slink=7wtosj&f1=";
	
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
	
	//TODO extract extra agency info addres, tel, city
	
	private static final WebDriver driver = Utils.setupWebDriver();

	private AgencyRepository agencyRepository;
	  
    private NeighbourhoodRepository neighbourhoodRepository;
   
    private CityRepository cityRepository;
    
    public ImotBgConnector(AgencyRepository agencyRepository,
    		NeighbourhoodRepository neighbourhoodRepository, CityRepository cityRepository) {
    	this.agencyRepository = agencyRepository;
    	this.cityRepository = cityRepository;
    	this.neighbourhoodRepository = neighbourhoodRepository;
    }
    
	@Override
	public Advert extractData(String url)  {

		driver.get(url);
		
		RealEstateType estateType = RealEstateType.getTypeFrom(Utils.getTextByXpath(driver, ESTATE_TYPE_XPATH));
		
		String fullAddress = Utils.getTextByXpath(driver, FULL_ADDRESS_XPATH);
		String[] neighborhoodParts;
		neighborhoodParts = fullAddress.split(COMMA_SEPARATOR);

		Integer price = Utils.parseInt(Utils.getTextByXpath(driver, PRICE_XPATH));
		Integer squareFootage = Utils.parseInt(Utils.getTextByXpath(driver, SQUARE_FOOTAGE));
		Integer floor = Utils.parseInt(Utils.getTextByXpath(driver, FLOOR_XPATH).substring(0, 2));

		String cityName = neighborhoodParts[0].substring(5);
		City city = new City();
        CityRepository cityRepo = cityRepository.getCityByName(cityName);
        if (cityRepo == null) {
        	city.setName(cityName);
        } else {
        	city.setId(city.getId());
        }
        String neighbourhoodName = neighborhoodParts[1].trim();
        Neighbourhood neighbourhood = new Neighbourhood();
        NeighbourhoodRepository neighbourhoodRepo = neighbourhoodRepository.getNeighbourhoodByName(neighbourhoodName);
        if (neighbourhoodRepo == null) {
        	neighbourhood.setName(neighbourhoodName);
            neighbourhood.setCity(city);
        } else {
        	neighbourhood.setId(neighbourhood.getId());
        }
        
        String agencyName = Utils.getTextByXpath(driver, AGENCY_XPATH);
        RealEstateAgency agency = new RealEstateAgency();
        agency.setName(agencyName);
        AgencyRepository agencyRepo = agencyRepository.getRealEstateAgencyByName(agencyName);
        if (agencyRepo != null) {
        	 agencyRepo = agencyRepository.getRealEstateAgencyByName(agencyName);
        	agency.setId(agency.getId());
        }
        
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
		driver.findElement(By.xpath("//a[@class='mapBtnProdajbi']")).click();
		driver.findElement(By.xpath("//input[@value='Т Ъ Р С И']")).click();
		
		String pageUrl = driver.getCurrentUrl();
		pageUrl = pageUrl.substring(0, pageUrl.length() - 1);
		log.info(pageUrl);
		
		Set<String> urlSet = new HashSet<>();
		
		String pagesNumber = Utils.getTextByXpath(driver, "//span[@class='pageNumbersInfo']");
		int lastPage = Integer.parseInt(pagesNumber.substring(pagesNumber.length() - 3).trim());
		
		int page = 1;
	
		while (page != lastPage) {
			int beginTableIndex = 6;
			int contentCount = 45;
			String nextPage = pageUrl + page;
			
			for (int j = beginTableIndex; j <= contentCount; j++) {
				String url = Utils.getLinkXpath(driver, "//table[" + j + "]//a");
				urlSet.add(url);
			}
			
			driver.get(nextPage);
			page++;
		}
		
		return urlSet;
	}
}