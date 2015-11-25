package com.worksap.stm.SWARMS.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.DiscountDto;
import com.worksap.stm.SWARMS.dto.ProfitDto;
import com.worksap.stm.SWARMS.utils.Utilities;


@Repository
public class MarkingDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private String REPLACE_PRE_WITH_CURR = "update marking set preDate = currDate, preProfitType = currProfitType, preProfitPercent = currProfitPercent, "
			+ "preDiscountType = currDiscountType, preDiscountPercent = currDiscountPercent where pid = ? ";
	private String UPDATE_CURR_DISCOUNT = "update marking set currDate = ?, currDiscountType = ?, currDiscountPercent = ? where pid = ?";
	private String UPDATE_CURR_PROFIT = "update marking set currDate = ?, currProfitType = ?, currProfitPercent = ? where pid = ?";
	private String GET_CURR_DATE = "SELECT currDate from marking where pid = ?";
	
	
	private boolean needToReplaceTheColumn(String pid){
		
		return template.queryForObject(GET_CURR_DATE, (rs,column)->{
			
			String date = rs.getString("currDate");
			String todayDate = Utilities.getCurrentDate();
			return (Utilities.findDayDiffernce(todayDate, date)>1);
		},pid);
		
	}
	
	private void replaceTheColumns(String pid){	
		template.update(REPLACE_PRE_WITH_CURR,(ps)->{
			ps.setString(1,pid);
		});
	}
	
	public void upateProfitMarkingGroup(String pid, String profitId){
			
			if(needToReplaceTheColumn(pid))
				replaceTheColumns(pid);
			ProfitDto profitDto = getProfitType(profitId);
			
			template.update(UPDATE_CURR_PROFIT,(ps)->{
				ps.setString(1,Utilities.getCurrentDate());
				ps.setString(2, profitDto.getName());
				ps.setInt(3, profitDto.getMargin());
				ps.setString(4,pid);
			});
		   
		}
		
	public void upateDiscountMarkingGroup(String pid, String discountId){
		if(needToReplaceTheColumn(pid))
			replaceTheColumns(pid);
		
		DiscountDto discountDto = getDiscountType(discountId);
		
		template.update(UPDATE_CURR_DISCOUNT,(ps)->{
			ps.setString(1,Utilities.getCurrentDate());
			ps.setString(2, discountDto.getName());
			ps.setInt(3, discountDto.getDiscount());
			ps.setString(4,pid);
		});
	   
			
			
	}
	
	
	public DiscountDto getDiscountType(String id){
		String sql = "select * from discount where id  = ?";
		return template.queryForObject(sql, (rs,column)->{
			DiscountDto discountDto = new DiscountDto();
			discountDto.setName(rs.getString("name"));
			discountDto.setDiscount(rs.getInt("discount"));
			return discountDto;
		},id);
	}

	public ProfitDto getProfitType(String id){
		String sql = "select * from profit where id  = ?";
		return template.queryForObject(sql, (rs,column)->{
			
			ProfitDto profitDto = new ProfitDto();
			profitDto.setName(rs.getString("name"));
			profitDto.setMargin(rs.getInt("margin"));
			return profitDto;
			
		},id);
	}
	
}
