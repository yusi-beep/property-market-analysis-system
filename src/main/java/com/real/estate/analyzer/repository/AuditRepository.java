package com.real.estate.analyzer.repository;

import com.real.estate.analyzer.entities.Audit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AuditRepository extends CrudRepository<Audit, Long> {}
