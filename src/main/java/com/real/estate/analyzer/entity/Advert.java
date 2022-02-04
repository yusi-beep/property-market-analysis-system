package com.real.estate.analyzer.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="advert")
public class Advert {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="square_footage")
	private String squareFootage;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="price")
	private String price;
	
	@Column(name="floor")
	private String floor;
	
	@Column(name="broker")
	private String broker;
	
}
