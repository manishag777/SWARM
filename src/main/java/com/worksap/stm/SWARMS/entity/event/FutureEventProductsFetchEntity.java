package com.worksap.stm.SWARMS.entity.event;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FutureEventProductsFetchEntity {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<ProductEntity> productEntities; 
}
