package com.worksap.stm.SWARMS.entity.analysis;

import java.util.List;

import com.worksap.stm.SWARMS.dto.analysis.CustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerReturnEntity {
	List<CustomerDto> totalCustomerEntities;
	List<CustomerDto> newCustomerEntities;
	List<CustomerDto> exisitingCustomerEntities;
}
