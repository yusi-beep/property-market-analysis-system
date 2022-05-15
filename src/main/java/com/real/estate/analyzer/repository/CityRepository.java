package com.real.estate.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entities.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
	
	City getCityByName(String name);
	
//	@Query("SELECT name FROM city")
//	public List<String> cityList();
}