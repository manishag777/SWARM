package com.worksap.stm.SWARMS.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceFeedbackDto {
	private int feedbackId;
	private String pid;
	private int customerId;
	private Date dateAdded;
	private Date dateResolved;
	private boolean higherThanAmazon;
	private boolean higherThanEbay;
	private String higherThanOthers;
	private boolean resolved;
}
