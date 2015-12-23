package com.worksap.stm.SWARMS.dao.analysis;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.analysis.RevenueDto;
import com.worksap.stm.SWARMS.dao.SportDao;


@Repository
public class RevenueSalesChartDao {
	
	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private SportDao sportDao;
	
	@Autowired
	private SportDao storeDao;
	
	private String revenueQuery =  "Select Sum(sub_total) AS 'Total' , DATE(order_date) as order_date  from orders where order_date between ? and ? GROUP BY DATE(order_date)";
	private String profitQuery = "Select sum(cp*qty*margin/100 - (cp*qty + cp*qty*margin/100)*discount/100) AS 'Total', DATE(order_date) as order_date from orders  inner join order_detail on orders.id = order_id where order_date between ? and ? GROUP BY DATE(order_date)  " ;
	private String storeWiseRevenueQuery = "Select sum(cp + cp*od.qty*margin/100 - (cp*od.qty + cp*od.qty*margin/100)*od.discount/100) As 'Total', p.sport_id sport_id from order_detail od inner join orders o on o.id = order_id inner join product_detail pd on pd.id = od.pid"
			+ " inner join product p on p.pid = pd.pid where o.store_id = ? and order_date between ? and ? group by p.sport_id";
	
	private String sportWiseRevenueQuery = "Select sum(cp + cp*od.qty*margin/100 - (cp*od.qty + cp*od.qty*margin/100)*od.discount/100) As 'Total', o.store_id store_id from order_detail od inner join orders o on o.id = order_id inner join product_detail pd on pd.id = od.pid"
			+ " inner join product p on p.pid = pd.pid where p.sport_id = ? and order_date between ? and ? group by o.store_id";
	
	private String storeWiseProfitQuery = "Select sum(cp*od.qty*margin/100 - (cp*od.qty + cp*od.qty*margin/100)*od.discount/100) As 'Total', p.sport_id sport_id from order_detail od inner join orders o on o.id = order_id inner join product_detail pd on pd.id = od.pid"
			+ " inner join product p on p.pid = pd.pid where o.store_id = ? and order_date between ? and ? group by p.sport_id";
	
	private String sportWiseProfitQuery = "Select sum(cp*od.qty*margin/100 - (cp*od.qty + cp*od.qty*margin/100)*od.discount/100) As 'Total', o.store_id store_id from order_detail od inner join orders o on o.id = order_id inner join product_detail pd on pd.id = od.pid"
			+ " inner join product p on p.pid = pd.pid where p.sport_id = ? and order_date between ? and ? group by o.store_id";

	
	
	public List<RevenueDto> returnDaysByRevenue(String fromDate, String toDate){
	    long total = 0;
		List<RevenueDto> revenuDtoList =  template.query(revenueQuery, new Object[]{fromDate,toDate}, (rs,column)->{
			RevenueDto revenueDto = new RevenueDto();
			revenueDto.setRevenue(rs.getLong("Total"));
			revenueDto.setDate(rs.getString("order_date"));
			return revenueDto;
		});
		
		for(int i=0; i<revenuDtoList.size(); i++){
			total += revenuDtoList.get(i).getRevenue();
		}
		for(int i=0; i<revenuDtoList.size(); i++){
			revenuDtoList.get(i).setPercentage((float)((revenuDtoList.get(i).getRevenue()*10000)/total)/(float)100);
		}
		
		return revenuDtoList;
	}; 
	
	public List<RevenueDto> returnProfitByDays(String fromDate, String toDate){
	    long total = 0;
		List<RevenueDto> revenuDtoList =  template.query(profitQuery, new Object[]{fromDate,toDate}, (rs,column)->{
			RevenueDto revenueDto = new RevenueDto();
			revenueDto.setProfit(rs.getLong("Total"));
			revenueDto.setDate(rs.getString("order_date"));
			return revenueDto;
		});
		
		for(int i=0; i<revenuDtoList.size(); i++){
			total += revenuDtoList.get(i).getProfit();
		}

		for(int i=0; i<revenuDtoList.size(); i++){
			revenuDtoList.get(i).setPercentage((float)((revenuDtoList.get(i).getProfit()*10000)/total)/(float)100);
		}
		
		return revenuDtoList;
	};
	

	
	public HashMap<String, Integer> getStoreWiseCount(String type,  String store_id, String fromDate, String toDate){
		
		String Query;
		
		if(type.equals("revenue"))
			Query = storeWiseRevenueQuery;
		else
			Query = storeWiseProfitQuery;
		
		HashMap<String, Integer> sportCountMap = new HashMap<>(); 
		template.query(Query, new Object[]{store_id, fromDate, toDate}, (rs,column)->{
			sportCountMap.put(rs.getString("sport_id"), rs.getInt("Total"));
			return null;
		});
		return sportCountMap;
	}
	
	public HashMap<String, Integer> getSportWiseCount(String type, String sport_id, String fromDate, String toDate){
		
		String Query="";
		
		if(type.equals("revenue"))
			Query = sportWiseRevenueQuery;
		else
			Query = sportWiseProfitQuery;
		
		HashMap<String, Integer> storeCountMap = new HashMap<>(); 
		template.query(Query, new Object[]{sport_id, fromDate, toDate}, (rs,column)->{
			storeCountMap.put(rs.getString("store_id"), rs.getInt("Total"));
			return null;
		});
		return storeCountMap;
	}
	
}
