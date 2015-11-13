package com.worksap.stm.SWARMS.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSearchFilterEntity {
	//private int groupType;
	//private String storeFilter;
	private int draw;
	private int start;
	private int length;
}
