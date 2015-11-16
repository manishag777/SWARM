package com.worksap.stm.SWARMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailDto {
	int id;
	int orderID;
	int pid;
	int qty;
	int cp;
	int margin;
	int discount;
}
