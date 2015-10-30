package com.worksap.stm.SWARMS.dto;

import com.worksap.stm.SWARMS.entity.EmployeeAccountCreationEntity;
import com.worksap.stm.SWARMS.entity.UserAccountCreationEntity;
import com.worksap.stm.SWARMS.utils.PasswordHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phoneNo;
	
	
	public EmployeeDto(EmployeeAccountCreationEntity entity) {
		username = entity.getUsername();
		if (entity.getPassword() != null) {
			PasswordHash hash = new PasswordHash();
			password = hash.encode(entity.getPassword());
		}
		
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		email = entity.getEmail();
		phoneNo = entity.getPhoneNo();
	}
	
	
}
