package com.worksap.stm.SWARMS.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListEntity {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<ProductFetchEntity> employeeEntities; 
}
