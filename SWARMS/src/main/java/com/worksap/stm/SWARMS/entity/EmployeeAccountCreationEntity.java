package com.worksap.stm.SWARMS.entity;


import com.worksap.stm.SWARMS.dto.EmployeeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAccountCreationEntity {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	
	public EmployeeAccountCreationEntity(EmployeeDto employeeAccount) {
		this.username = employeeAccount.getUsername();
		this.password = employeeAccount.getPassword();
		this.firstName = employeeAccount.getFirstName();
		this.lastName = employeeAccount.getLastName();
		this.email = employeeAccount.getLastName();
		this.phoneNo = employeeAccount.getPhoneNo();
	}
}
