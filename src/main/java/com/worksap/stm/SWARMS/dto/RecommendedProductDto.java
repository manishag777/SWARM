package com.worksap.stm.SWARMS.dto;


import java.util.List;

import com.worksap.stm.SWARMS.entity.event.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecommendedProductDto {
	
	private String type;
	private int estimatedQty;
	private List<ProductEntity> productEntityList;
	
}

