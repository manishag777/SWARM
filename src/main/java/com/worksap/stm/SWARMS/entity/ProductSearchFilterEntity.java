package com.worksap.stm.SWARMS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSearchFilterEntity {
	
	private String sportId;
	private String brand;
	private String priceRange;
	private String discountRange;
	private String marginType;
	private String searchText;
	private int draw;
	private int start;
	private int length;
}
