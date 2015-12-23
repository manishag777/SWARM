package com.worksap.stm.SWARMS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComparativePrices {
	String pid;
	int ourPrices;
	int amazonPrices;
	int ebayPrices;
}
