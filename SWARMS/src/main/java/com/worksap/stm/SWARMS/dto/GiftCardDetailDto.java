package com.worksap.stm.SWARMS.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GiftCardDetailDto {
	private int id;
	private int amt;
	private Date expiry;
}
