package com.real.estate.analyzer.connectors;

import java.util.List;

import com.real.estate.analyzer.entity.Advert;

public interface Connector {

	// extracting data from web page
	Advert extractData( String url) ;
	
	// url buffer
	List<String> urlArray() ;
 
	void extractAdverts();
	
}

 