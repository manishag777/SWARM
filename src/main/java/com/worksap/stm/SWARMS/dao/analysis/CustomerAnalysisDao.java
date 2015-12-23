package com.worksap.stm.SWARMS.dao.analysis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.analysis.CustomerDto;
import com.worksap.stm.SWARMS.dto.analysis.RevenueDto;

@Repository
public class CustomerAnalysisDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private String customerTotalQuery = "select store_id, count(store_id) as count  from orders where order_date between ? and ? GROUP BY DATE(store_id)";
	//private String NewcustomerQuery = "select store_id, count(store_id)  from orders where order_date between ? and ? GROUP BY DATE(store_id)";
	//private String ExistingcustomerQuery = "select store_id, count(store_id)  from orders where order_date between ? and ? GROUP BY DATE(store_id)";
	
	private float nf = 0.9f;
	private float gf = 0.7f ;
	
	
	public List<CustomerDto> getCustomerCount(String fromDate, String toDate, String query){
		
		List<CustomerDto> customerDtoList =  template.query(query, new Object[]{fromDate,toDate}, (rs,column)->{
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCount(rs.getInt("count"));
			customerDto.setStoreId(rs.getString("store_id"));
			return customerDto;
		});
		
		
		
		return customerDtoList;
	}
	
	public List<CustomerDto> getTotalCostumerCount(String fromDate, String toDate){
		List<CustomerDto> customerDtoList =  getCustomerCount(fromDate, toDate, customerTotalQuery);
	    CustomerDto customerDto1 = new CustomerDto();
		customerDto1.setCount((int)(customerDtoList.get(0).getCount()*nf));
		customerDto1.setStoreId("Noida");
		
		CustomerDto customerDto2 = new CustomerDto();
		customerDto2.setCount((int)(customerDtoList.get(0).getCount()*gf));
		customerDto2.setStoreId("Gurgaon");
		customerDtoList.add(customerDto1);
		customerDtoList.add(customerDto2);

		long total = 0;
		
		for(int i=0; i<customerDtoList.size(); i++){
			total += customerDtoList.get(i).getCount();
		}
		for(int i=0; i<customerDtoList.size(); i++){
			customerDtoList.get(i).setPercentage((float)((customerDtoList.get(i).getCount()*10000)/total)/(float)100);
		}
		return customerDtoList;
	}; 
	
//	public List<CustomerDto> getNewCostumerCount(String fromDate, String toDate){
//	    return getCustomerCount(fromDate, toDate, NewcustomerQuery);
//	};
//	
//	public List<CustomerDto> getExisitingCostumerCount(String fromDate, String toDate){
//	    return getCustomerCount(fromDate, toDate, ExistingcustomerQuery);
//	};
	
	
}
