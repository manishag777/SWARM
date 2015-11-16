package com.worksap.stm.SWARMS.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.OrderDto;
import com.worksap.stm.SWARMS.dto.WishListDto;

@Repository
public class WishListDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT = "INSERT INTO wishlist"
			+ " (pd_id, cust_id, price, enable_price_down, enable_product_available)"
			+ " VALUES (?, ?, ?, ?, ?)";
	
	private static final String UPDATE = "UPDATE  wishlist set enable_price_down = ?, enable_product_available = ?, price = ? where   "
			+ "pd_id = ? and cust_id = ? ";
	
	public void insert(WishListDto wishListDto) throws IOException {
		try {
				template.update(INSERT, (ps) -> {
				System.out.println(wishListDto);	
				ps.setInt(1, wishListDto.getId());
				ps.setInt(2, wishListDto.getCustId());
				ps.setInt(3,wishListDto.getPrice());
				ps.setInt(4, wishListDto.getEnableWhenPriceDown() );
				ps.setInt(5, wishListDto.getEnableWhenProductAvailable() );
				});
						
		} catch (DataAccessException e) {
			
			try {
				template.update(UPDATE, (ps) -> {
				System.out.println(wishListDto);	
				ps.setInt(1,wishListDto.getEnableWhenPriceDown());
				ps.setInt(2, wishListDto.getEnableWhenProductAvailable());
				ps.setInt(3,wishListDto.getPrice());
				ps.setInt(4, wishListDto.getId() );
				ps.setInt(5, wishListDto.getCustId());
				});
			}
				catch (DataAccessException e2) {
					System.out.println("At OrderDao :" +e);
					throw new IOException(e2);
				}
				
			
		}
	}
	
}
