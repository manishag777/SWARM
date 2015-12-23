package com.worksap.stm.SWARMS.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventDto {
	
	int id;
	String eventName;
	String eventDetail;
	String eventDate;
	List<String> sportId;
	List<String> storeId;
	String sportType;
	String fromDate;
	String toDate;
	int participantCount;
	int pinCode;
	String address;
	int expectedRevenue;
	int expectedCustomerVisit;
	int revenueGenerated;
	int customerVisited;
	int targetedRevenue;
	int targetedCustomer;
	int task1;
	int task2;
	int task3;
	
}
