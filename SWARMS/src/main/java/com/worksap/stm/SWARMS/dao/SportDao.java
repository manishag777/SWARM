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


@Repository
public class SportDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT = "INSERT INTO employee_sport (username, sport_id) "
			+ " VALUES (? , ?)";
	
	public void insertEmployeeSport(String username, String sportId) throws IOException  {
		try {
			template.update(INSERT, (ps) -> {
			ps.setString(1, username);
			ps.setString(2, sportId);
		});
	} catch (DataAccessException e) {
		System.out.println("At SportDao :" +e);
		throw new IOException(e);
	}
	}



}
