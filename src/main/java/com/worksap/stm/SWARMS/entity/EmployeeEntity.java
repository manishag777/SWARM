package com.worksap.stm.SWARMS.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.worksap.stm.SWARMS.dto.EmployeeDto;

@NoArgsConstructor
@Data
public class EmployeeEntity implements Serializable {
	
	private static final long serialVersionUID = -7395917071437157624L;

	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
//	private List<String> role;
//	private String storeId;
//	private String officeId;

	public EmployeeEntity(EmployeeDto employee) {
		this.username = employee.getUsername();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.email = employee.getEmail();
		this.phoneNo = employee.getPhoneNo();
	}

}
