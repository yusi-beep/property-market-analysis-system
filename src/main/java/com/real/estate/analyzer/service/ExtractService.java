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
		/*
		connector = new ImotBgConnector();
		urlLinks = (HashSet<String>) connector.urlSet();
		checkLinks(urlLinks);
		saving(urlLinks);
		
		connector = new HomesBgConnector();
		urlLinks = (HashSet<String>) connector.urlSet();
		checkLinks(urlLinks);
		saving(urlLinks);
		*/
		
		testSaving();
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
			
			boolean duplicates = Utils.isDuplicate(advert.getAddress(),
						advert.getCity(), advert.getFloor(), advert.getSquareFootage(), advert.getBroker());

			if (advert.getTitle() != "" && duplicates) {
				
				advertRepository.save(advert);
				Utils.sleep(2000);
				
			} else {
				
				System.out.println(advert);
			}
		}	
	}
	
	public void testSaving() {
		
		List<Advert> adList = read();
		
		for (Advert advert : adList) {
			
			boolean duplicates;
//TODO move try catch to utils
			try {
				 duplicates = advertRepository.checkForDuplicates(
						advert.getAddress(),
						advert.getCity(),
						advert.getFloor(),
						advert.getSquareFootage(),
						advert.getBroker()) == null;
				 
			} catch (org.springframework.dao.IncorrectResultSizeDataAccessException e ) {
				
				duplicates = false;
			}
			
			if (advert.getTitle() != "" && duplicates) {
				
				advertRepository.save(advert);
				Utils.sleep(2000);
				
			} else {
				
				System.out.println(advert);
			}
		}	
	}
	
	public List<Advert> read() {
		List<Advert> advertList = new ArrayList<Advert>();
		Advert advert = new Advert("Продава 1-СТАЕН", 45, "Боян Българанов 1", "Шумен", 65000, 3, "ПРЕСТИЖ-2007", "//www.homes.bg1", LocalDateTime.now());
		advertList.add(advert); 
		advert = new Advert("Продава 2-СТАЕН", 60, "Боян Българанов 1", "Шумен", 82000, 6, "ПЕРФЕКТ-2006", "//www.homes.bg2", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Двустаен апартамент за продажба", 70, "Боян Българанов 1", "Шумен", 95000, 14, "Аджест хоум", "//www.homes.bg3", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Продава 1-СТАЕН", 45, "Боян Българанов 1", "Шумен", 65000, 3, "ПРЕСТИЖ-2007", "//www.homes.bg4", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Едностаен апартамент за продажба", 48, "Боян Българанов 1", "Шумен", 53000, 6, "Имоти Томов", "//www.homes.bg5", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Имот 5", 55, "Самара", "Шумен", 115000, 2, "Рако ", "//www.homes.bg6", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Имот 6", 60, "Самара", "Шумен", 116000, 2, "Рако ", "//www.homes.bg7", LocalDateTime.now());
		advertList.add(advert);
		
		return advertList;
	}
	
	
	/*
	try {
		 duplicates = advertRepository.checkForDuplicates(
				advert.getAddress(),
				advert.getCity(),
				advert.getFloor(),
				advert.getSquareFootage(),
				advert.getBroker()) == null;
		 
	} catch (org.springframework.dao.IncorrectResultSizeDataAccessException e ) {
		
		duplicates = false;
	}
	*/
}
