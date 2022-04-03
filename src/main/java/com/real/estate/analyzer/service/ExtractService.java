package com.real.estate.analyzer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.HomesBgConnector;
import com.real.estate.analyzer.connectors.ImotBgConnector;
import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.entity.City;
import com.real.estate.analyzer.entity.Neighborhood;
import com.real.estate.analyzer.entity.RealEstateAgency;
import com.real.estate.analyzer.repository.AdvertRepository;
import com.real.estate.analyzer.utils.Utils;

@Component
@Service
public class ExtractService implements CommandLineRunner {

	@Autowired
    private AdvertRepository advertRepository;
	
	private HashSet<String> urlLinks;
	
	private Connector connector;
	
	@Override
	public void run(String... args) throws Exception {			
		
		connector = new ImotBgConnector();
		urlLinks = (HashSet<String>) connector.urlSet();
		checkLinks(urlLinks);
		saving(urlLinks);
		
		connector = new HomesBgConnector();
		urlLinks = (HashSet<String>) connector.urlSet();
		checkLinks(urlLinks);
		saving(urlLinks);
		
	}
	
	public void checkLinks(HashSet<String> urlLink) {
		
		for (Advert advert : advertRepository.findAll()) {
			
	        if (urlLink.contains(advert.getUrl())) {
	        	urlLink.remove(advert.getUrl());
	        }
	    }
	}
	
	public void saving(HashSet<String> urlLink) {
		
		Advert advert;
		
		for (String url : urlLinks) {
			
			advert = connector.extractData(url);
		
			boolean duplicates;
			/*
			try {
				 duplicates = advertRepository.checkForDuplicates(
						advert.getNeighborhoodId(),
						advert.getFloor(),
						advert.getSquareFootage()) == null;
				 
			} catch (org.springframework.dao.IncorrectResultSizeDataAccessException e ) {
				
				duplicates = false;
			}
			*/
			if (advert.getNeighborhoodId() != null) {
				advertRepository.save(advert);
				Utils.sleep(2000);
			
			}/* else {
				
				System.out.println(advert);
			}*/
		}	
	}
}
