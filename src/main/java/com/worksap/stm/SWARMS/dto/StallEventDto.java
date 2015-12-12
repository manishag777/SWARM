package com.worksap.stm.SWARMS.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class StallEventDto {
	int id;
	String eventName;
	String placeEvent;
	String eventDate;
	String fromDate;
	String toDate;
	String coName;
	String coPhone;
	String coEmail;
	String detail;
	int cmStatus;
	String cmTime;
	String mAddress;
	String reason;
	String sportType;
	int mStatus;
	int fees;
	int stallNo;
	String trainingTime;
	int discount;
	int eventStatus;
	int rCustomer;
	int profit;
	int revenue;
	int aCustomer;
	int taskStatus;
	List<String> userList;
	int isAdded;
	int isSeen;
}
