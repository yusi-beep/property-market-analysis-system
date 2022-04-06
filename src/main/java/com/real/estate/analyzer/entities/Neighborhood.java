package com.real.estate.analyzer.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Neighborhood {
	
    @Id
    @GeneratedValue
    private Long id;
    
	// @Column(unique = true) // possible issues as you don't check for existing row
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city")
	private City city;
}
