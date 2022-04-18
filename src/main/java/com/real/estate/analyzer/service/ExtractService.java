package com.real.estate.analyzer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.HomesBgConnector;
import com.real.estate.analyzer.connectors.ImotBgConnector;
import com.real.estate.analyzer.entities.Advert;
import com.real.estate.analyzer.repository.AdvertRepository;
import com.real.estate.analyzer.repository.AgencyRepository;
import com.real.estate.analyzer.repository.CityRepository;
import com.real.estate.analyzer.repository.NeighbourhoodRepository;
import com.real.estate.analyzer.utils.Utils;

@Slf4j
@Service
public class ExtractService implements CommandLineRunner {

    private static final int TIMEOUT = 2000;

    @Autowired
    private AdvertRepository advertRepository;
    
    @Autowired
    private AgencyRepository agencyRepository;
    
    @Autowired
    private NeighbourhoodRepository neighbourhoodRepository;
    
    @Autowired
    private CityRepository cityRepository;

    @Override
    public void run(String... args) {

    	List<Connector> listConectors = new ArrayList<Connector>();
    	listConectors.add(new ImotBgConnector(agencyRepository, neighbourhoodRepository, cityRepository));
    	listConectors.add(new HomesBgConnector(agencyRepository, neighbourhoodRepository, cityRepository));
    	
    	for (Connector connector : listConectors) {
    		extractAdvertsFrom(connector);
    	}
    }

    private void extractAdvertsFrom(Connector connector) {
        Set<String> urlLinks = connector.urlSet();

        for (Advert advert : advertRepository.findAll()) {
            urlLinks.remove(advert.getUrl());
        }

        for (String url : urlLinks) {
            try {
                Advert advert = connector.extractData(url);

//            boolean duplicates = advertRepository.checkForDuplicates(
//                    advert.getNeighborhood(),
//                    advert.getFloor(),
//                    advert.getSquareFootage()) == null;
                
                advertRepository.save(advert);
            } catch (RuntimeException e) {
                log.info(String.format("Problem with [%s]", url), e);
            }

            Utils.sleep(TIMEOUT);
        }
    }
}