package com.worksap.stm.SWARMS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfitMarkingEntity {
	int id;
	String previousMarking;
	String currentMarking;
	String previousDate;
	String currentDate;
	String markingFilter;
}
