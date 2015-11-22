package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.UserAccountDto;


@Repository
public class EmployeeDao {
	@Autowired
	private JdbcTemplate template;
	
	private static final String FETCH_BY_username = "SELECT * FROM employee WHERE username = ?";
	private static final String INSERT_EMPLOYEE = "INSERT INTO employee "
			+ " (username, password, firstname, lastname, email, phoneno)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String FETCH = "SELECT * FROM employee";
	
	
	public void insert(EmployeeDto employee) throws IOException {
		try {
				template.update(INSERT_EMPLOYEE, (ps) -> {
				ps.setString(1, employee.getUsername());
				ps.setString(2, employee.getPassword());
				ps.setString(3, employee.getFirstName());
				ps.setString(4, employee.getLastName());
				ps.setString(5, employee.getEmail());
				ps.setString(6, employee.getPhoneNo());
			});
		} catch (DataAccessException e) {
			
			System.out.println("At EmployeeDao :" +e);
			throw new IOException(e);
		}
	}
	
		
	public List<EmployeeDto> getAllEmployeeData() throws IOException{
		try {
		return template.query(
				FETCH,
				(rs,rownum)->{
					return new EmployeeDto(rs.getString("username"),
							rs.getString("firstname"), 
							rs.getString("lastname"), 
							rs.getString("password"),
							rs.getString("email"),
							rs.getString("phoneno"));
				});
		}
		catch (DataAccessException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
	}
	
	
	
	public EmployeeDto getByUsername(String username)  {
		
			return template.queryForObject(
					FETCH_BY_username,
					(rs, rownum) -> {
						return new EmployeeDto(rs.getString("username"),
								rs.getString("firstname"), 
								rs.getString("lastname"), 
								rs.getString("password"),
								rs.getString("email"),
								rs.getString("phoneno"));
					}, username);
		
	}
}
