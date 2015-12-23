package com.worksap.stm.SWARMS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceComparisonEntity {
	String pid;
	int procurmentPrice;
	int mrp;
	int discount;
	int sellingPrice;
	int amazonPrice;
	int ebayPrice;
}
