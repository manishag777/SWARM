package com.worksap.stm.SWARMS.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductFetchEntity {
	String pid;
	String name;
	String type;
	String brand;
	String color;
	String size;
	String margin;
	int id;  //Id of product-detail
	int qty;
	int wqty;
}
