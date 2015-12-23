package com.worksap.stm.SWARMS.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Geometry {
	public double lng, lat;
//	Geometry(double lng, double lat){
//		this.lng = lng;
//		this.lat = lat;
//	}
	
	@Override
	public String toString(){
		return lng+"_"+lat ;
	}
}
