package com.worksap.stm.SWARMS.entity.analysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalesChartGetEntity {
	private int draw;
	private int start;
	private int length;
	private String fromDate;
	private String toDate;
	private String storeId;
	private String sportId;
}
