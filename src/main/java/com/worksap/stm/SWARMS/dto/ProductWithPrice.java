package com.worksap.stm.SWARMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductWithPrice {
	String pid;
	String productDetails;
	int procurmentPrice;
	int mrp;
	int currentDiscount;
}