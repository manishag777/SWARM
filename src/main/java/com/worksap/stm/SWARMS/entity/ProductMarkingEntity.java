package com.worksap.stm.SWARMS.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductMarkingEntity {
	String pid;
	Date startDate;
	Date endDate;
	int procurementPrice;
	int mrp;
	int discountPercent;
	int averageProfit;
}
