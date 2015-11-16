package com.worksap.stm.SWARMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class GiftCardDto {
	private int issueAmt;
	private int amt1;
	private int thresholdAmt;
	private int amt2;
	private int validity;
}
