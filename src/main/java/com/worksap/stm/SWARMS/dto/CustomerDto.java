package com.worksap.stm.SWARMS.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {
	private String id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNo;
	private List<String> sportsInterest;
	private String DOB;
	private int gender;
	private int pinCode;
	private String city;
	private String state;
	private String country;
	private int referrerId;
	private int isNewCustomer;
	
}
