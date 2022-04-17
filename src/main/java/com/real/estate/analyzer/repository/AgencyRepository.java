package com.real.estate.analyzer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entities.RealEstateAgency;

@Repository
public interface AgencyRepository extends CrudRepository<RealEstateAgency, Long> {

	AgencyRepository getRealEstateAgencyByName(String name);
}