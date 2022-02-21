package com.real.estate.analyzer.connectors;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.repository.AdvertRepository;

public interface Connector {

	public static final Logger log = LoggerFactory.getLogger(Connector.class);

	// extracting data from web page
	Advert extractData( String url) ;
	
	// url buffer
	Set<String> urlSet();
 
	// multiple extraction
	void extractAdverts();
	
}

 