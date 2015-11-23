package com.worksap.stm.SWARMS.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//import com.worksap.stm.SWARMS.utils.PopulateData.MarkingStatus;

import lombok.AllArgsConstructor;

@Repository
public class PopulateMarkingTable {
	
	@Autowired
	private JdbcTemplate template;
	
	private class MarkingStatus{
		int pid;
		String predate, currDate, preProfitType, preDiscountType, currProfitType, currDiscountType;
		int preProfitPercent, preDiscountPercent, currProfitPercent, currDiscountPercent;
	    MarkingStatus(){};
	    
	    public String toString(){
	    	
	    	return pid+"";
	    	//return ld+" "+lm+" "+cd+" "+cm;
	    }
	}
	
	@AllArgsConstructor
	private class markingObject{
		int id;
		int percent;
		String name;
	}
	
	HashMap<Integer, markingObject> discountMap = new HashMap<>();
	HashMap<Integer, markingObject> profitMap = new HashMap<>();
	
	private List<Integer> getAllProductId(){
		
		String SqlQuery = "SELECT id from product_detail";
		return template.query(SqlQuery, (rs,column)->{
			 	return rs.getInt("id");
			});
			
	}
	
	public void populateMarkingTable(){		
		InitilazeMap(0);
		InitilazeMap(1);
		List<Integer> pids = getAllProductId();
		int nDiscount = discountMap.size(), nProfit = profitMap.size();
		
		for(int i=0; i<pids.size(); i++){
			 int a  = getRandomInt(1,nDiscount), b =  getRandomInt(1,nProfit);
			 while(!isValid(a,b)){
				 a  = getRandomInt(1,nDiscount);
				 b = getRandomInt(1,nProfit);
			 }
			 int c = getRandomInt(1,nDiscount), d = getRandomInt(1,nProfit);
			 while( (a==c && b==d) || !isValid(c,d)){
				 c  = getRandomInt(1,nDiscount);
				 d = getRandomInt(1,nProfit);
			 }
			 
			 updateTable(pids.get(i),a,b,c,d);
		}
		
	}
	
	public void InitilazeMap(int type){
		//int count = 1;
		if(type==0){
			String sql = "select * from profit";
			 
			template.query(sql, (rs,column)->{
				profitMap.put(rs.getInt("id"), new markingObject(rs.getInt("id"),rs.getInt("margin"),rs.getString("name")));
				return null;
			});
			
		}
		else{
			//count = 1;
			String sql = "select * from discount ";
			template.query(sql, (rs,column)->{
				discountMap.put(rs.getInt("id"), new markingObject(rs.getInt("id"),rs.getInt("discount"),rs.getString("name")));
				return null;
			});
		}
		
	}
		
	public static int getRandomInt(int min, int max){
	 	Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
	
	public boolean isValid(int disRef, int proRef){
		int dis = discountMap.get(disRef).percent;
		int prof = profitMap.get(proRef).percent;
		return (100*prof - dis*(100+prof) > 0); 
	}

	public void updateTable(int pid, int preDisRef, int preProRef, int currDisRef, int currProRef){
		int maxDayAgo = 30, minDayAgo = 1;
		maxDayAgo = getRandomInt(minDayAgo, maxDayAgo);
	    String preDate = getDate(maxDayAgo);
	    maxDayAgo = getRandomInt(minDayAgo-1, maxDayAgo-1);
	    String currDate = getDate(maxDayAgo);
	    
	    // updateSqlTable
	    String sql = "INSERT into marking (pid, preDate, preProfitType, preProfitPercent , preDiscountType, preDiscountPercent"
	    		+ ", currDate, currProfitType, currProfitPercent, currDiscountType, currDiscountPercent"
	    		+ " ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    template.update(sql,(ps)->{
	    	ps.setInt(1,pid);
	    	ps.setString(2, preDate);
	    	ps.setString(3,profitMap.get(preProRef).name);
	    	ps.setInt(4, profitMap.get(preProRef).percent);
	    	ps.setString(5,discountMap.get(preDisRef).name);
	    	ps.setInt(6, discountMap.get(preDisRef).percent);
	    	ps.setString(7, currDate);
	    	ps.setString(8,profitMap.get(currProRef).name);
	    	ps.setInt(9, profitMap.get(currProRef).percent);
	    	ps.setString(10,discountMap.get(currDisRef).name);
	    	ps.setInt(11, discountMap.get(currDisRef).percent);
	    	
	    });
	    
	    
	    
	    
	}
	
	private String getDate(int daysAgo){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	    Date date = new Date(); // Or where ever you get it from
	    Date dateBefore = new Date(date.getTime() - daysAgo * 24 * 3600 * 1000l);
	    String res = dateFormat.format(dateBefore);
	    return res;

	}
}
