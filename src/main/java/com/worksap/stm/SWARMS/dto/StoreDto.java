package com.worksap.stm.SWARMS.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreDto {
	String id;
	String name;
	String address;
	Double lat;
	Double lng;
	
	public StoreDto(String address, Double lat, Double lng  ){
		this.address = address;
		this.lat = lat;
		this.lng = lng;
	}
}
