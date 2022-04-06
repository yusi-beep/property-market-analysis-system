package com.real.estate.analyzer.connectors;

import java.util.Set;

import com.real.estate.analyzer.entities.Advert;

public interface Connector {

	// extracting data from web page
	Advert extractData(String url);
	
	// url buffer
	Set<String> urlSet();	
}

 