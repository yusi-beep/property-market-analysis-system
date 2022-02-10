package com.real.estate.analyzer.connectors;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.real.estate.analyzer.entity.Advert;

public interface Connector {

	// extracting data from web page
	public Advert extractData(WebDriver driver, String url) throws InterruptedException;
	
	// url buffer
	public List<String> urlArray(WebDriver driver) throws InterruptedException;
	
	// get working page url 
	public String getWorkPageUrl(WebDriver driver) throws InterruptedException;
}
