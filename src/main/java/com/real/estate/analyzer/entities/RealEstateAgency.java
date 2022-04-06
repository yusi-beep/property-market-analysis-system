package com.real.estate.analyzer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RealEstateAgency {
	
    @Id
    @GeneratedValue
    private Long id;
    
	@Column(unique = true)
	private String name;
}
