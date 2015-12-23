package com.worksap.stm.SWARMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
	private String productId;
	private String sportId;
	private String productName;
	private String brandName;
	private String productInfo;
	private String sizes;
	private String colors;
	private String imageUrl;

}

