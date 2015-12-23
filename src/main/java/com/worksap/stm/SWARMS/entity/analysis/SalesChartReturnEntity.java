package com.worksap.stm.SWARMS.entity.analysis;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.worksap.stm.SWARMS.dto.analysis.RevenueDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesChartReturnEntity {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<RevenueDto> revenueEntities;
	private List<RevenueDto> profitEntities;
	private HashMap<String, HashMap<String, Integer>> sportStoreMap;
	private HashMap<String, HashMap<String, Integer>> storeSportMap;
}
