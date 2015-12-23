package com.worksap.stm.SWARMS.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopSeperateProductEntity {
	int pid;
	String modelNo;
	String name;
	int amt;
	float percentage;
	float cumpercentage;
	
}
