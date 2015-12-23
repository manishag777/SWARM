package com.worksap.stm.SWARMS.utils;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Repository
public class PopulateData {
	
	@Autowired
	private JdbcTemplate template;
	
	private class MarkingStatus{
		int pid;
		String ld, cd;
		int lm, cm;
	    MarkingStatus(){};
	    
	    public String toString(){
	    	
	    	return ld+" "+lm+" "+cd+" "+cm;
	    }
		
	}
	
	public void populateMarkingStatusTable(){
		System.out.println(getAllProductId());
		List<Integer> pids = getAllProductId();
		for(int i=0; i<pids.size(); i++){
			MarkingStatus m = getMarkingStatusGenerator();
			m.pid = pids.get(i);
			System.out.println(m);
			populateProfitStatusTable(m);
			
		}
	}

	
	private List<Integer> getAllProductId(){
		
		String SqlQuery = "SELECT id from product_detail";
		return template.query(SqlQuery, (rs,column)->{
			 	return rs.getInt("id");
			});
			
	}
	
	private MarkingStatus getMarkingStatusGenerator(){
		
		MarkingStatus m = new MarkingStatus();
	    Random rand = new Random();
	    int max = 30, min = 1;
	    max = rand.nextInt((max - min) + 1) + min;
	    m.ld = getDate(max);
	    max = rand.nextInt((max-1 - 0) + 1) + 0;
	    m.cd = getDate(max);
	    m.lm = rand.nextInt((4 - 1) + 1) + 1;
	    m.cm = rand.nextInt((4 - 1) + 1) + 1;
	    
	    return m;
	    	    	    
	}
	
	private String getDate(int daysAgo){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	    Date date = new Date(); // Or where ever you get it from
	    Date dateBefore = new Date(date.getTime() - daysAgo * 24 * 3600 * 1000l);
	    String res = dateFormat.format(dateBefore);
	    return res;

	}
	
	private void populateProfitStatusTable(MarkingStatus ms){
		String SqlQuery = "INSERT into marking_status (pid, lmarking, ldate, cmarking, cdate) values (?, ?, ?, ?, ?)";
		 template.update(SqlQuery, (ps)->{
			 
			ps.setInt(1, ms.pid);
			ps.setInt(2, ms.lm);
			ps.setString(3, ms.ld);
			ps.setInt(4, ms.cm);
			ps.setString(5, ms.cd);
		});
		 
		 return;

	}
	
	
	
}
