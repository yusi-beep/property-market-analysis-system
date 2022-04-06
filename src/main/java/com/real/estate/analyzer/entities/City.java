package com.real.estate.analyzer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue
    private Long id;

	// @Column(unique = true) // possible issues as you don't check for existing row
	private String name;
}
