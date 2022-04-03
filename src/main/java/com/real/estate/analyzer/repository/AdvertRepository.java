package com.real.estate.analyzer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.entity.Neighborhood;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {
	/*
	@Query(value = "select * \n" +
            "from advert \n" +
            "where neighborhood = :neighborhood \n" +
            "and floor = :floor \n" +
            "and square_footage = :squareFootage \n",
            nativeQuery = true)
    Advert checkForDuplicates(
    		@Param("neighborhood") Neighborhood neighborhood,
    		@Param("floor") Integer floor,
    		@Param("squareFootage") Integer squareFootage);
	
	@Query(value = "select MIN(price)\n" +
					"from advert")
	Advert getMinPrice();
	
	@Query(value = "select MAX(price)\n" +
			"from advert")
	Advert getMaxPrice();
	
	@Query(value = "select AVG(price)\n" +
			"from advert")
	Advert getAvgPrice();
	*/
}
