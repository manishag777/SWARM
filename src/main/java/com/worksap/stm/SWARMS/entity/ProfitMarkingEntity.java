package com.worksap.stm.SWARMS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfitMarkingEntity {
	int id;
	String preDate;
	String preProfitType;
	int preProfitPercent;
	String preDiscountType;
	int preDiscountPercent;
	String currDate;
	String currProfitType;
	int currProfitPercent;
	String currDiscountType;
	int currDiscountPercent;
	float profitStatus;
	float volumeStatus;
	String profitSelect;
	String discountSelect;
}
