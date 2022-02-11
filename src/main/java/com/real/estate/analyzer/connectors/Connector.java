package com.real.estate.analyzer.connectors;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.real.estate.analyzer.entity.Advert;

public interface Connector {

	// extracting data from web page
	public Advert extractData(WebDriver driver, String url) ;
	
	// url buffer
	public List<String> urlArray(WebDriver driver) ;

	//check xpath contains
	public static String checkXpathContains(WebDriver driver, String xPath){

		WebElement element = driver.findElement(By.xpath(xPath));

		if(element != null){
			return element.getText();
		}

		return null;
	}
}
