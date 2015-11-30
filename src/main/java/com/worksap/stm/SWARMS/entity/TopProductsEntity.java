package com.worksap.stm.SWARMS.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopProductsEntity {
	
	int topCount;
	int sales;
	float percentage;
	float cumPercentage;
	List<TopSeperateProductEntity> productEntities;
	
}
