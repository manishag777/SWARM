package com.worksap.stm.SWARMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
	int id;
	int custId;
	int subTotal;
	int gcDiscount;
	String storeId;
}
