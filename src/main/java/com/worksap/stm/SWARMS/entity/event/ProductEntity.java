package com.worksap.stm.SWARMS.entity.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductEntity {
	int pid;
	String modelNo;
	String name;
	int expectedSales;
	int remainingSales;
	int availableQty;
	int price;
	
}
