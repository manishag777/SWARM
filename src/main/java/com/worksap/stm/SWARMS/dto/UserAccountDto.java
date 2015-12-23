package com.worksap.stm.SWARMS.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.worksap.stm.SWARMS.dto.UserAccountDto;
import com.worksap.stm.SWARMS.entity.UserAccountCreationEntity;
import com.worksap.stm.SWARMS.utils.PasswordHash;

@NoArgsConstructor
@Data
public class UserAccountDto {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String role;
	
	
	public UserAccountDto(UserAccountCreationEntity entity) {
		username = entity.getUsername();
		if (entity.getPassword() != null) {
			PasswordHash hash = new PasswordHash();
			password = hash.encode(entity.getPassword());
		}
		
		firstName = entity.getFirstName();
		lastName = entity.getLastName();
		address = entity.getAddress();
		
	}
	
}
