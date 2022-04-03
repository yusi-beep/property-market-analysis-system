package com.real.estate.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.entity.RealEstateAgency;

public interface AgencyRepository extends CrudRepository<RealEstateAgency, Long>{
/*
	List<Advert> findAllByName(String agency);
	//TODO 
	@Query(value = "select MIN(ad.price)\n" +
			"from advert ad\n" +
			"join agency ag on ag.ID = ad.agency_id\n" +
			"where ag.name = :name\n")
	Advert getMinPrice(@Param("name") String name);
	
	@Query(value = "select MAX(ad.price)\n" +
			"from advert ad\n" +
			"join agency ag on ag.ID = ad.agency_id\n" +
			"where ag.name = :name\n")
	Advert getMaxPrice(@Param("name") String name);
	
	@Query(value = "select AVG(ad.price)\n" +
			"from advert ad\n" +
			"join agency ag on ag.ID = ad.agency_id\n" +
			"where ag.name = :name\n")
	Advert getAvgPrice(@Param("name") String name);
	*/
}
