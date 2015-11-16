package com.worksap.stm.SWARMS.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;
import com.worksap.stm.SWARMS.utils.Utilities;

@Repository
public class GiftCardDetailDao {

	@Autowired
	private JdbcTemplate template;
	
	private static final String FETCH_BY_ID = "SELECT g.* FROM giftcard AS g JOIN customer as c ON g.id = c.giftcard_id "
			+ "where c.id = ?";
	
	private static final String INSERT_GIFTCARD   = "INSERT INTO giftcard "
			+ " (expiry, amt) "
			+ " VALUES (?, ?)";
	
	private static final String FETCH_ID = "SELECT LAST_INSERT_ID() as id" ;
	
	private static final String UPDATE_GIFTCARD =  "UPDATE giftcard SET expiry = ?,"
			+" amt = ? "
			+ "where id = ?";
	
	public GiftCardDetailDto GetgiftCardStatusByCustomerId(int id) throws IOException  {
		
		try{
			return template.queryForObject(
					FETCH_BY_ID,
					(rs, rownum) -> {
						return new GiftCardDetailDto(rs.getInt("id"),
								rs.getInt("amt"),
								(""+rs.getDate("expiry"))
								);
					}, id);	
		}
		catch(Exception e){
			System.out.println("At GiftCardDetailDao " + e);
			return null;
		}
	}
	
	public int insertGiftCard(GiftCardDetailDto giftCardDetailDto) throws IOException {
		try {
			template.update(INSERT_GIFTCARD, (ps) -> {
			ps.setDate(1, java.sql.Date.valueOf(giftCardDetailDto.getExpiry()));
			ps.setInt(2, giftCardDetailDto.getAmt());
			});
			
 
		 int id = template.queryForObject(
				 FETCH_ID, Integer.class);
		 System.out.println("id = " + id);
		
		return id;
		
		}
		catch(Exception e){
			System.out.println("At GiftCardDetailDao " + e);
			return 0;
		}
	}
	
	public int updateGiftCard(GiftCardDetailDto giftCardDetailDto) throws IOException {
		try {
			template.update(UPDATE_GIFTCARD, (ps) -> {
			ps.setDate(1, java.sql.Date.valueOf(giftCardDetailDto.getExpiry()));
			ps.setInt(2, giftCardDetailDto.getAmt());
			ps.setInt(3, giftCardDetailDto.getId());
			});
			
		 int id = template.queryForObject(
				 FETCH_ID, Integer.class);
		 System.out.println("id = " + id);
		 
		return id;
		
		}
		catch(Exception e){
			System.out.println("At GiftCardDetailDao " + e);
			return 0;
		}
	}
	
}
