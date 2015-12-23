package com.worksap.stm.SWARMS.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.GiftCardDto;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.utils.Utilities;

@Repository
public class GiftCardDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT = "INSERT INTO giftcard_specification "
			+ " (issue_Amt, amount1, threshold_amt, amount2, expiry_extension)"
			+ " VALUES (?, ?, ?, ?, ?)";
	
	private static final String DELETE = "DELETE FROM giftcard_specification ";	
	private static final String FETCH = "SELECT * FROM giftcard_specification";

			
	
	public void updateGiftCard(GiftCardDto giftCardDto) throws IOException{
		
		try {
			
			template.update(DELETE);
			
			template.update(INSERT, (ps) -> {
				ps.setInt(1, giftCardDto.getIssueAmt());
				ps.setInt(2, giftCardDto.getAmt1());
				ps.setInt(3,giftCardDto.getThresholdAmt());
				ps.setInt(4, giftCardDto.getAmt2());
				ps.setInt(5, giftCardDto.getValidity());		
				});
		}
		catch(Exception e){
			System.out.println("At GiftCardDao :" +e);
			throw new IOException(e);
		}
	}

	public  GiftCardDto fetchGiftCardDetail() throws IOException{
		
		try {

		return template.queryForObject(
				FETCH,
				(rs, rownum) -> {
					return new GiftCardDto(rs.getInt("issue_amt"),
							rs.getInt("amount1"), 
							rs.getInt("threshold_amt"), 
							rs.getInt("amount2"),
							rs.getInt("expiry_extension"));
				});
					
		}
		
		catch(Exception e){
			System.out.println("At GiftCardDao :" +e);
			throw new IOException(e);
		}
		
			
	}
	
}







