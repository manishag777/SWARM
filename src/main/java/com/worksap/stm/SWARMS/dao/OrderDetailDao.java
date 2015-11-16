package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.worksap.stm.SWARMS.dto.OrderDetailDto;

@Repository
public class OrderDetailDao {
	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private ProductDetailDao productDetailDao;
	
	private static final String INSERT_ORDER_DETAIL = "INSERT INTO order_detail"
			+ " (order_id, pid, qty, cp, margin, discount)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	private static final String FETCH_ID =  "SELECT LAST_INSERT_ID() as id";
	
	public void insert(List<OrderDetailDto> orderDetailDtoList, int orderId) throws IOException {
		try {
			 
			 template.batchUpdate(INSERT_ORDER_DETAIL, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, orderId);
						ps.setInt(2, orderDetailDtoList.get(i).getPid());
						ps.setInt(3, orderDetailDtoList.get(i).getQty());
						ps.setInt(4, orderDetailDtoList.get(i).getCp());
						ps.setInt(5, orderDetailDtoList.get(i).getMargin());
						ps.setInt(6, orderDetailDtoList.get(i).getDiscount());
						
					}

					@Override
					public int getBatchSize() {
						return orderDetailDtoList.size();
					}
				});
			 
			 
			 				
		} catch (DataAccessException e) {
			
			System.out.println("At OrderDetailDao :" +e);
			throw new IOException(e);
		}
	}
}
