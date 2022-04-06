package com.real.estate.analyzer.connectors;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.real.estate.analyzer.entities.Advert;
import com.real.estate.analyzer.entities.City;
import com.real.estate.analyzer.entities.Neighborhood;
import com.real.estate.analyzer.entities.RealEstateAgency;
import com.real.estate.analyzer.enums.RealEstateType;
import com.real.estate.analyzer.utils.Utils;

@Slf4j
public class HomesBgConnector implements Connector {

    private static final String WORKPAGE_URL_LINK = "https://www.homes.bg/?currencyId=1&filterOrderBy=0&locationId=0&typeId=ApartmentSell";

    private static final String COMMA_SEPARATOR = ",";

    private static final String GET_ELEMENT = "//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div[4]//a";

    private static final String FULL_TITLE_XPATH = "//div[@class='section-title']//h1";

    private static final String TOTAL_ADVERTS = "//span[@class='milestone-total']";

    private static final String PRICE_XPATH = "//span[@class='ver20black']";

    private static final String FLOOR_XPATH = "//div[@class='item']//text()[contains(.,'Етаж')]/following::h3";

    private static final String FULL_ADDRESS_XPATH = "//div[@class='mdc-layout-grid__inner']//b//h2";

    private static final String AGENCY_XPATH = "//div[@class='contact-box']//div[1]/b";

//	private static final String BROKER_XPATH = "//text()[contains(.,'брокер')]//preceding-sibling::span";

//	private static final String BROKER_PHONE_XPATH = "//text()[contains(.,'брокер')]//following::a";

    //TODO extract extra agency info
    private static final String AGENCY_ADDRESS_XPATH = " ";

    private static final String AGENCY_TEL_XPATH = " ";

    private static final String AGENCY_CITY_XPATH = " ";

    private static final WebDriver driver = Utils.setupWebDriver();

    @Override
    public Advert extractData(String url) {
        driver.get(url);

        String fullTitle = Utils.getTextByXpath(driver, FULL_TITLE_XPATH);
        String[] parts = Utils.split(COMMA_SEPARATOR, fullTitle);

        RealEstateType estateType = RealEstateType.getTypeFrom(parts[0].trim());
        Integer squareFootage = Utils.parseInt(parts[1]);
        Integer price = Utils.parseInt(Utils.getTextByXpath(driver, PRICE_XPATH));
        Integer floor = Utils.parseInt(Utils.getTextByXpath(driver, FLOOR_XPATH).substring(0, 3));

        String fullAddress = Utils.getTextByXpath(driver, FULL_ADDRESS_XPATH);
        parts = Utils.split(COMMA_SEPARATOR, fullAddress);

        City city = new City();
        city.setName(parts[1].trim());

        Neighborhood neighbourhood = new Neighborhood();
        neighbourhood.setName(parts[0].trim());
        neighbourhood.setCity(city);

        //String broker = Utils.getTextByXpath(driver, BROKER_XPATH);

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

        driver.get(WORKPAGE_URL_LINK);

        Set<String> urlSet = new HashSet<>();

        WebElement getElement = driver.findElement(By.xpath(GET_ELEMENT));
        Utils.scroll(getElement, driver);
        Utils.sleep(500);

        Integer numberOfUrls = Utils.parseInt(Utils.getTextByXpath(driver, TOTAL_ADVERTS));

        log.info(String.format("%d", numberOfUrls));

        if (numberOfUrls == null) {
            return urlSet;
        }

        String link = getElement.getAttribute("href");
        int getId = Integer.parseInt(link.substring(link.length() - 7));

        for (int i = 1; i < numberOfUrls; i++) {
            String url = "https://www.homes.bg//as" + getId;
            urlSet.add(url);
            getId--;
        }

        return urlSet;
    }
}