package com.real.estate.analizer.dtos;

import org.openqa.selenium.WebDriver;

import com.real.estate.analyzer.entity.Advert;

public interface Extract {

	public Advert extractData(WebDriver driver, String url) throws InterruptedException;
}
