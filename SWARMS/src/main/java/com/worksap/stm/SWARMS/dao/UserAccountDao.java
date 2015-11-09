package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.UserAccountDto;

@Repository
public class UserAccountDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String sql = "SELECT u.username name, u.password pass, a.role role FROM "+
		     "user u INNER JOIN user_role a on u.username=a.username WHERE "+
		     "a.enabled =1 and u.username = ?";
//	String sql2 = SELECT * FROM AUTHOR WHERE id = ?""
	
	public UserAccountDto getByUsername(String username) {
		
			return jdbcTemplate.queryForObject(sql, (rs,rownum) ->{
			UserAccountDto userAccountDto = new UserAccountDto();
			userAccountDto.setUsername(rs.getString("name"));
			userAccountDto.setPassword(rs.getString("pass"));
			//userAccountDto.setRole(rs.getString("role"));
			return userAccountDto;
			},username
		);
			
		
	}
	
	public void insert(UserAccountDto userAccount) {
		
		return;
	}
}
