package com.worksap.stm.SWARMS.entity;



import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.worksap.stm.SWARMS.dto.UserAccountDto;

@NoArgsConstructor
@Data
public class UserAccountCreationEntity {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private List<String> roles;
	private String address;
	
	public UserAccountCreationEntity(UserAccountDto userAccount, List<String> roles) {
		this.username = userAccount.getUsername();
		this.password = userAccount.getPassword();
		this.firstName = userAccount.getFirstName();
		this.lastName = userAccount.getLastName();
		this.roles = roles;
		this.address = userAccount.getAddress();
	}
}
