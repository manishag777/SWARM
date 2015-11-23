package com.worksap.stm.SWARMS.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductFilterEntity {
	private int profitType;
	private String storeType;
	private String sportType;
	private String sellingType;
	private int draw;
	private int start;
	private int length;
}
