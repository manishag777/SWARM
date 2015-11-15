package com.worksap.stm.SWARMS.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductSearchFilterEntity {

	private String pid;
	private String brand;
	private String name;
	private int draw;
	private int start;
	private int length;
}
