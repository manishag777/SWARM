package com.worksap.stm.SWARMS.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;

@Repository
public class GiftCardDetailDao {

	@Autowired
	private JdbcTemplate template;
	
	private static final String FETCH_BY_ID = "SELECT g.* FROM giftcard AS g JOIN customer as c ON g.id = c.giftcard_id "
			+ "where c.id = ?";
	
	public GiftCardDetailDto GetgiftCardStatusByCustomerId(int id) throws IOException  {
		
		try{
			return template.queryForObject(
					FETCH_BY_ID,
					(rs, rownum) -> {
						return new GiftCardDetailDto(rs.getInt("id"),
								rs.getInt("amt"),
								rs.getDate("expiry")
								);
					}, id);	
		}
		catch(Exception e){
			System.out.println("At GiftCardDetailDao " + e);
			return null;
		}
	}
}
