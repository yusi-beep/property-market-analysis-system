package com.real.estate.analyzer.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Advert {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String squareFootage;
	
	@Column
	private String address;
	
	@Column
	private String city;
	
	@Column
	private String price;
	
	@Column
	private String floor;
	
	@Column
	private String broker;

	@Column(unique=true)
	private String url;
	
	@Column
	private LocalDateTime data;

	public Advert(String title, String squareFootage, String address, String city, String price, String floor,
			String broker, String url, LocalDateTime data) {
		this.title = title;
		this.squareFootage = squareFootage;
		this.address = address;
		this.city = city;
		this.price = price;
		this.floor = floor;
		this.broker = broker;
		this.url = url;
		this.data = data;
	}
	
	
}
