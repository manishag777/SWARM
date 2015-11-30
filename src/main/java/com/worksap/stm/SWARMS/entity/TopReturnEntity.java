package com.worksap.stm.SWARMS.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopReturnEntity {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<TopProductsEntity> topProductsEntities; 
}
