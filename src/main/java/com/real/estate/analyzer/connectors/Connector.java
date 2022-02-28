package com.real.estate.analyzer.connectors;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.real.estate.analyzer.entity.Advert;

public interface Connector {

	public static final Logger log = LoggerFactory.getLogger(Connector.class);

	// extracting data from web page
	Advert extractData(String url);
	
	// url buffer
	Set<String> urlSet();	
}

 