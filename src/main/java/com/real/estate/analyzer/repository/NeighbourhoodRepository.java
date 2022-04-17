package com.real.estate.analyzer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entities.Neighbourhood;

@Repository
public interface NeighbourhoodRepository extends CrudRepository<Neighbourhood, Long>{
	
	NeighbourhoodRepository getNeighbourhoodByName(String name);
}
