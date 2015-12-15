package com.worksap.stm.SWARMS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePriceEntity {
	String pid;
	int procurmentPrice;
	int mrp;
	int currentDiscount;
	int ourPrice;
	int amazonPrice;
	int ebayPrice;
}
