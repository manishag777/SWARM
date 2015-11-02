package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.ProductDto;

@Repository
public class ProductDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT_PRODUCT = "INSERT INTO product "
			+ " (pid, sport_id, name, brand, info, aval_size, aval_color, iurl)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String FETCH = "SELECT * FROM product";
	
	public void insert(ProductDto product) throws IOException {
		try {
				template.update(INSERT_PRODUCT, (ps) -> {
				ps.setString(1, product.getProductId());
				ps.setString(2, "cricket");		//change it later according to the manager type
				ps.setString(3, product.getProductName());
				ps.setString(4, product.getBrandName());
				ps.setString(5, product.getProductInfo());
				ps.setString(6, product.getSizes());
				ps.setString(7, product.getColors());
				ps.setString(8, product.getImageUrl());

			});
		} catch (DataAccessException e) {
			
			System.out.println("At EmployeeDao :" +e);
			throw new IOException(e);
		}
	}
	
public List<ProductDto> getAllProduct()  {
		
		return template.query(
				FETCH,
				(rs, rownum) -> {
					return new ProductDto(rs.getString("pid"),
							rs.getString("sport_id"), 
							rs.getString("name"),
							rs.getString("brand"),
							rs.getString("info"),
							rs.getString("aval_size"),
							rs.getString("aval_color"),
							rs.getString("iurl"));
				});
	
}


}
