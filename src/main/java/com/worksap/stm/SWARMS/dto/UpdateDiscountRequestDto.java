package com.worksap.stm.SWARMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDiscountRequestDto {
	String productId;
	int procurmentPrice;
	int newDiscount;
	int mrp;
}
