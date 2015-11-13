package com.worksap.stm.SWARMS.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSearchFetchListEntity {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<ProductSearchFetchEntity> productSearchFetchEntities; 

}
