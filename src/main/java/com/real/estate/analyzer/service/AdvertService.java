package com.real.estate.analyzer.service;

import java.math.BigDecimal;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface AdvertService {
	
	public BigDecimal minPriceForNeighbourhood(String name);
	
	public BigDecimal maxPriceForNeighbourhood(String name);
	
	public BigDecimal avgPriceForNeighbourhood(String name);
	
	public BigDecimal minPriceForAgnecy(String name);
	
	public BigDecimal maxPriceForAgnecy(String name);
	
	public BigDecimal avgPriceForAgnecy(String name);
}
