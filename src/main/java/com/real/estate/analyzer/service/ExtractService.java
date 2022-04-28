package com.real.estate.analyzer.service;

import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.entities.Advert;
import com.real.estate.analyzer.repository.AdvertRepository;
import com.real.estate.analyzer.utils.Utils;

@Slf4j
@Service
public class ExtractService  implements CommandLineRunner {

    private static final int TIMEOUT = 2000;

    @Autowired
    private AdvertRepository advertRepository;
    
    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) {

        Map<String, Connector> listConectors = context.getBeansOfType(Connector.class);
        
        
        for (Connector connector : listConectors.values()) {
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
//                    advert.getNeighbourhood(),
//                   advert.getFloor(),
//                    advert.getSquareFootage()) != null;
            
            if (advert.getSquareFootage() != 0) {
                advertRepository.save(advert);
            }
            
            } catch (RuntimeException e) {
                log.info(String.format("Problem with [%s]", url), e);
            }

            Utils.sleep(TIMEOUT);
        }
    }
}