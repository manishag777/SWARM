package com.worksap.stm.SWARMS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSearchFetchEntity {
	
	private String pid;
	private int pdetailId;
	private String name;
	private String brand;
	private String color;
	private String size;
	private int price;
	private int discount;
	private String isAvailable;
	private int qty;
	private String info;
	private String url;
}
