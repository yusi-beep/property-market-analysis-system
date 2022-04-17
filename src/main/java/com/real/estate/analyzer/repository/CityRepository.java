package com.real.estate.analyzer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entities.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
	
	CityRepository getCityByName(String name);
}