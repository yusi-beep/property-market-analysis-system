package com.real.estate.analyzer.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.HomesBgConnector;
import com.real.estate.analyzer.connectors.ImotBgConnector;
import com.real.estate.analyzer.repository.AdvertRepository;
import com.real.estate.analyzer.utils.Utils;

@Component
@Service
public class ExtractService implements CommandLineRunner{

	@Autowired
    private AdvertRepository repository;
	
	private HashSet<String> urlLinks;
	
	private Connector connector;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		connector = new ImotBgConnector();
		urlLinks = (HashSet<String>) connector.urlSet();
		
		for (String url : urlLinks) {
			
			repository.save(connector.extractData(url));
			Utils.sleep(2000);		
		}
	
		connector = new HomesBgConnector();
		urlLinks = (HashSet<String>) connector.urlSet();
		
		for (String url : urlLinks) {
			
			repository.save(connector.extractData(url));
			Utils.sleep(2000);		
		}	
	}
}
