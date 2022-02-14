package com.real.estate.analyzer.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Advert {

	@Id
	@GeneratedValue
	private Long id;
	
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
	private LocalDateTime date;

	public Advert(String title, String squareFootage, String address, String city, String price, String floor,
			String broker, String url, LocalDateTime date) {
		this.title = title;
		this.squareFootage = squareFootage;
		this.address = address;
		this.city = city;
		this.price = price;
		this.floor = floor;
		this.broker = broker;
		this.url = url;
		this.date = date;
	}
	
	
}
