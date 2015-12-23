package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.StoreDto;

@Repository
public class StoreDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT = "INSERT INTO employee_store (username, store_id) "
			+ " VALUES (? , ?)";
	
	private static final String fetchAllStore = "SELECT * FROM store" ;
	
	private static final String fetchStoreById = "SELECT * FROM store where id = ?" ;
	
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
	
	//hardcoded later it will be implemented properly
	public List<StoreDto> getAllStore(){
		StoreDto s1 = new StoreDto("connaught Place",28.629041, 77.223657);
		List<StoreDto> storeList = new ArrayList<>();
		storeList.add(s1);
		return storeList;
	}
	
	public List<StoreDto> fetchStoreDto(){
		
		return template.query(fetchAllStore,(rs,column)->{
			StoreDto storeDto = new StoreDto();
			storeDto.setId(rs.getString("id"));
			storeDto.setName(rs.getString("name"));
			storeDto.setLat(rs.getDouble("lat"));
			storeDto.setLng(rs.getDouble("lng"));
			storeDto.setPinCode(rs.getInt("pincode"));
			//storeDto.setP(rs.getInt("pincode"));
			return storeDto;
		});
		
	}
	
	public StoreDto fetchStoreDtoById(String id){
			return template.queryForObject(fetchStoreById,(rs,column)->{
				StoreDto storeDto = new StoreDto();
				storeDto.setId(rs.getString("id"));
				storeDto.setName(rs.getString("name"));
				storeDto.setLat(rs.getDouble("lat"));
				storeDto.setLng(rs.getDouble("lng"));
				storeDto.setPinCode(rs.getInt("pincode"));
				return storeDto;
			},id);
			
	}
		
	

}
