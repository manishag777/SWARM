package com.worksap.stm.SWARMS.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {
	String StoreId;
	int count;
	float percentage;
}
