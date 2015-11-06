package com.worksap.stm.SWARMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDetailDto {
	String productId;
	String storeId;
	String size;
	String color;
	int price;
	int qty;
	int margin;
	int discount;
	int warningQty;
}
