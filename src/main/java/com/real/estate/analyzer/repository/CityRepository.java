package com.real.estate.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
/*
	List<Advert> findAllByName(String city);
	//TODO 
	@Query(value = "select MIN(ad.price)\n" +
			"from advert ad\n" +
			"join neighborhood n on n.ID = ad.neighborhood_id\n" +
			"where n.city_id = (select c.id\n" +
			"from city\n" +
			"join neighborhood n on c.id = n.city_id\n" +
			"where c.name = :name)")
	Advert getMinPrice(@Param("name") String name);
	
	@Query(value = "select MAX(ad.price)\n" +
			"from advert ad\n" +
			"join neighborhood n on n.ID = ad.neighborhood_id\n" +
			"where n.city_id = (select c.id\n" +
			"from city\n" +
			"join neighborhood n on c.id = n.city_id\n" +
			"where c.name = :name)")
	Advert getMaxPrice(@Param("name") String name);
	
	@Query(value = "select AVG(ad.price)\n" +
			"from advert ad\n" +
			"join neighborhood n on n.ID = ad.neighborhood_id\n" +
			"where n.city_id = (select c.id\n" +
			"from city\n" +
			"join neighborhood n on c.id = n.city_id\n" +
			"where c.name = :name)")
	Advert getAvgPrice(@Param("name") String name);
*/
}
