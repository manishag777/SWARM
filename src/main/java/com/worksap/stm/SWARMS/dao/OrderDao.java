package com.worksap.stm.SWARMS.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.OrderDto;

@Repository
public class OrderDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT_ORDER = "INSERT INTO orders"
			+ " (cust_id, sub_total, gc_discount, store_id, salestaff_id)"
			+ " VALUES (?, ?, ?, ?, ?)";
	private static final String FETCH_ID =  "SELECT LAST_INSERT_ID() as id" ;

	
	public int insert(OrderDto orderDto) throws IOException {
		
		System.out.println(orderDto);
		if(orderDto.getStaffId()==null || orderDto.getStaffId().equals("0"))
			orderDto.setStaffId(null);
			System.out.println(orderDto);
		try {
				template.update(INSERT_ORDER, (ps) -> {
				ps.setInt(1, orderDto.getCustId());
				ps.setInt(2, orderDto.getSubTotal());
				ps.setInt(3,orderDto.getGcDiscount());
				ps.setString(4, orderDto.getStoreId() );
				ps.setString(5, orderDto.getStaffId() );
				
				});
				
			 int id = template.queryForObject(
					 FETCH_ID, Integer.class);
			 System.out.println("id = " + id);
			 return id;
				
		} catch (DataAccessException e) {
			
			System.out.println("At OrderDao :" +e);
			throw new IOException(e);
		}
	}

}
