package com.worksap.stm.SWARMS.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.utils.Utilities;

@Repository
public class ProductDetailDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT_PRODUCT_DETAIL = "INSERT INTO product_detail "
			+ " (pid, store_id, size, color, price, qty)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String PRODUCT_COUNT = "SELECT COUNT(*) FROM product_detail WHERE pid = ? and store_id = ? and size = ? and color = ?" ;
	
	private static final String UPDATE_PRODUCT = "UPDATE product_detail SET qty = qty + ?, price = ? WHERE pid = ? and store_id = ? and size = ? and color = ?";
	
	private static final String FETCH_PRODUCT_DETAIL = "SELECT * FROM product_detail WHERE pid = ? and store_id = ? and size = ? and color = ? " ;
	
	public void insert(ProductDetailDto product) throws IOException {
		try {
				template.update(INSERT_PRODUCT_DETAIL, (ps) -> {
				ps.setString(1, product.getProductId());
				ps.setString(2, product.getStoreId());		//change it later according to the manager type
				ps.setString(3, product.getSize());
				ps.setString(4, product.getColor());
				ps.setInt(5, product.getPrice());
				ps.setInt(6, product.getQty());

			});
		} catch (DataAccessException e) {
			System.out.println("At EmployeeDao :" +e);
			throw new IOException(e);
		}
	}
	
	public void update(ProductDetailDto product) throws IOException {
		int count = template.queryForObject(
				PRODUCT_COUNT,new Object[]{product.getProductId(), product.getStoreId(), product.getSize(), product.getColor()} ,Integer.class);
		 System.out.println("count = " + count);
		 if(count == 0) insert(product);
		 else{
			 template.update(UPDATE_PRODUCT, (ps) -> {
					ps.setInt(1, product.getQty());
					ps.setInt(2, product.getPrice());
					ps.setString(3, product.getProductId());
					ps.setString(4, product.getStoreId());
					ps.setString(5, product.getSize());
					ps.setString(6, product.getColor());
					
			 });
		 }
		 
}
	
	public ProductDetailDto getProductDetail(String pid, String storeId,
			String size, String color) throws IOException {
		
		return template.queryForObject(
				FETCH_PRODUCT_DETAIL,new Object[]{pid,storeId, size,color} ,(rs,rownum)->{
					
					ProductDetailDto productDetailDto = new  ProductDetailDto();
					productDetailDto.setQty(rs.getInt("qty"));
					productDetailDto.setMargin(rs.getInt("margin"));
					productDetailDto.setWarningQty(rs.getInt("warning_qty"));
					productDetailDto.setDiscount(rs.getInt("discount"));
					productDetailDto.setPrice(rs.getInt("price"));
					
					return productDetailDto;
				
				});
				}
		
		

	
}
