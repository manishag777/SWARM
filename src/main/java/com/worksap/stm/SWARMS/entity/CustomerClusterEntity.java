package com.worksap.stm.SWARMS.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.StoreDto;
import com.worksap.stm.SWARMS.utils.Geometry;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerClusterEntity {
	ArrayList<HashSet<CustomerDto>> clusters;
	ArrayList<Geometry> centroids;
	ArrayList<Double> radius;
	List<StoreDto> store;
	ArrayList<Integer> clusterIndexes;
	int unusedIndex;
}
