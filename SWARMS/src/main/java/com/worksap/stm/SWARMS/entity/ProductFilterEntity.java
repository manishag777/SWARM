package com.worksap.stm.SWARMS.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductFilterEntity {
	private int groupType;
	private String storeFilter;
	private int draw;
	private int start;
	private int length;
}
