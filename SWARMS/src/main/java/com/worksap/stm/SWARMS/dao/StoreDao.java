package com.worksap.stm.SWARMS.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StoreDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT = "INSERT INTO employee_store (username, store_id) "
			+ " VALUES (? , ?)";
	
	public void insertEmployeeSport(String username, String storeId) throws IOException  {
		try {
			template.update(INSERT, (ps) -> {
			ps.setString(1, username);
			ps.setString(2, storeId);
		});
	} catch (DataAccessException e) {
		System.out.println("At StoreDao :" +e);
		throw new IOException(e);
	}
	}


}
