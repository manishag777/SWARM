package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.RoleDto;


@Repository
public class RoleDao  {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String FETCH_BY_ID = "SELECT * FROM employee_role WHERE username = ?";
	
	
	public List<RoleDto> getRoleByUsername(String username)  {
		
			return template.query(FETCH_BY_ID, ps -> ps.setString(1, username), (
					rs, rownum) -> {
				return new RoleDto(rs.getString("role"));
			});
		
	}
}
