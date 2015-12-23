package com.worksap.stm.SWARMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListDto {
	int id;
	int custId;
	int price;
	int enableWhenPriceDown;
	int enableWhenProductAvailable;
}
