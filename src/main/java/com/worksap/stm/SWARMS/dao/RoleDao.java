package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.RoleDto;


@Repository
public class RoleDao  {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String FETCH_BY_ID = "SELECT * FROM employee_role WHERE username = ?";
	private static final String INSERT = "INSERT INTO employee_role (username, role, enabled) "
			+ " VALUES (? , ?, ?)";
	
	
	public List<RoleDto> getRoleByUsername(String username)  {
		
			return template.query(FETCH_BY_ID, ps -> ps.setString(1, username), (
					rs, rownum) -> {
				return new RoleDto(rs.getString("role"));
			});
		
	}
	
	public void insertEmployeeRole(String username, List<String> roleList) throws IOException  {
		try {
			template.batchUpdate(INSERT, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					ps.setString(1, username);
					ps.setString(2, roleList.get(i));
					ps.setInt(3, 1);
				}

				@Override
				public int getBatchSize() {
					return roleList.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("At RoleDao :" +e);
			throw new IOException(e);
		}
	}
	
}
