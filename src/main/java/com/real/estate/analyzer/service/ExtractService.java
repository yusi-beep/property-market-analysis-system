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
		
		List<Advert> advertList = read();
		testSaving(advertList);
		
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
			
			if (advert.getTitle() != "") {
				
				for(Advert ad : advertRepository.findByAddress(advert.getAddress())) {
					
					int maxTolerance = advert.getSquareFootage() + 5;
					int minTolerance = advert.getSquareFootage() - 5;
					
					if (advert.getCity() == ad.getCity() &&
							advert.getFloor() == ad.getFloor() &&
							(ad.getSquareFootage() <= maxTolerance || ad.getSquareFootage() >= minTolerance)) {
						}
				}
				
				advertRepository.save(advert);
				Utils.sleep(2000);
				}
			
		}	
	}
	
	public List<Advert> read() {
		List<Advert> advertList = new ArrayList<Advert>();
		Advert advert = new Advert("Имот 1", 60, "Самара", "Шумен", 117000, 2, "Рако ", "//www.homes.bg", LocalDateTime.now());
		advertList.add(advert); 
		advert = new Advert("Имот 1", 49, "Самара", "Пловдив", 111000, 2, "Рако ", "//www.homes.bg", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Имот 1", 58, "Самара", "Шумен", 112000, 2, "Рако ", "//www.homes.bg", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Имот 3", 55, "Самара", "Шумен", 113000, 2, "Рако ", "//www.homes.bg", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Имот 3", 51, "Самара", "Пловдив", 124000, 2, "Рако ", "//www.homes.bg", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Имот 5", 55, "Самара", "Шумен", 115000, 2, "Рако ", "//www.homes.bg", LocalDateTime.now());
		advertList.add(advert);
		advert = new Advert("Имот 6", 55, "Самара", "Шумен", 116000, 2, "Рако ", "//www.homes.bg", LocalDateTime.now());
		advertList.add(advert);
		
		return advertList;
	}
	
	public void testSaving(List<Advert> advertList) {
		Advert advert;
		List<Advert> advertListSave = new ArrayList<Advert>();
		for (int i = 0; i < advertList.size(); i++) {
			
			advert = advertList.get(i);
			
			for(Advert ad : advertList) {
				
				Integer maxTolerance = advert.getSquareFootage() + 5;
				Integer minTolerance = advert.getSquareFootage() - 5;
				
				if (advert.getTitle() == ad.getTitle() && advert.getCity() == ad.getCity() &&
						advert.getFloor() == ad.getFloor() &&
						(advert.getSquareFootage() <= maxTolerance || advert.getSquareFootage() >= minTolerance)) {
					System.out.println(ad);
					advertListSave.add(advert);	
				}	
			}
			System.out.println("---------------------------------------------------");
		}
	}
}
