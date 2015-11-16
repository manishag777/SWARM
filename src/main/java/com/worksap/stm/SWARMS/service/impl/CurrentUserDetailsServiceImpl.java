package com.worksap.stm.SWARMS.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.EmployeeDao;
import com.worksap.stm.SWARMS.dao.RoleDao;
import com.worksap.stm.SWARMS.dao.UserAccountDao;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.RoleDto;
import com.worksap.stm.SWARMS.dto.UserAccountDto;
import com.worksap.stm.SWARMS.service.spec.CurrentUserDetailsService;

@Service
public class CurrentUserDetailsServiceImpl implements CurrentUserDetailsService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private RoleDao roleDao;
	

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		EmployeeDto employeeDto = employeeDao.getByUsername(username);
		if (employeeDto == null) {
        	throw new UsernameNotFoundException(String.format("User with id=%s was not found", username));
        }		
		
		List<RoleDto> roleDtoList = roleDao.getRoleByUsername(username);
		List<String> roles = new ArrayList<String>();
		
		for(RoleDto rd : roleDtoList){
			roles.add(rd.getName());
		}
		
		//GrantedAuthority authority = new SimpleGrantedAuthority(roleDtoList.get(0).getName());
		UserDetails userDetails = (UserDetails)new User(employeeDto.getUsername(), employeeDto.getPassword(), AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()])));
		
		return userDetails;
	}

}
