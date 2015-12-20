package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.dto.ProductWithPrice;

@Repository
public class ProductDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String INSERT_PRODUCT = "INSERT INTO product "
			+ " (pid, sport_id, name, brand, info, aval_size, aval_color, iurl)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_PRODUCT =  "UPDATE PRODUCT SET name = ?, "
	+" brand = ?, info = ?, aval_size = ?, aval_color = ?, iurl = ?"
	+ "where pid = ?";
	
	private static final String FETCH = "SELECT * FROM product where sport_id like ?";
	private static final String FETCH_BY_PID = "SELECT * FROM product where pid = ?";
	private static final String FETCH_BRAND_BY_SPORT_ID = "SELECT DISTINCT brand FROM product where sport_id like ?";
	private static final String FETCH_BY_FILTER = "SELECT * FROM product where sport_id like ? and brand like ? and name like ?";
	
	
	
	public void insert(ProductDto product) throws IOException {
		try {
				template.update(INSERT_PRODUCT, (ps) -> {
				ps.setString(1, product.getProductId());
				ps.setString(2, product.getSportId());		//change it later according to the manager type
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
	
	public void update(ProductDto product) throws IOException {
		try {
				template.update(UPDATE_PRODUCT, (ps) -> {
				ps.setString(1, product.getProductName());
				ps.setString(2, product.getBrandName());
				ps.setString(3, product.getProductInfo());
				ps.setString(4, product.getSizes());
				ps.setString(5, product.getColors());
				ps.setString(6, product.getImageUrl());
				ps.setString(7, product.getProductId());

			});
		} catch (DataAccessException e) {
			
			System.out.println("At EmployeeDao :" +e);
			throw new IOException(e);
		}
	}
	
public List<ProductDto> getAllProduct(String sport_id)  {
		
	 if(sport_id.equals("0"))
		 sport_id="%";
		
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
				},sport_id);
	
}

public List<ProductDto> getProductsListByFilter(String sport_id, String brand, String name )  {
	
	 if(sport_id.equals("0"))
		 sport_id="%";
	 
	 if(brand == null ||brand.equals("0")||brand.equals(""))
		 brand = "%";
	 if(name==null||name.equals(""))
		 name ="%";
	 
	 name = "%"+name+"%";
	System.out.println("At getAllProduct s = "+sport_id+" b= "+brand + " n = "+name);

	 
		return template.query(
				FETCH_BY_FILTER, new Object[]{sport_id, brand, name},
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





	public ProductDto getProductById(String id){
		return template.queryForObject(
				FETCH_BY_PID,
				(rs, rownum) -> {
					return new ProductDto(rs.getString("pid"),
							rs.getString("sport_id"), 
							rs.getString("name"),
							rs.getString("brand"),
							rs.getString("info"),
							rs.getString("aval_size"),
							rs.getString("aval_color"),
							rs.getString("iurl"));
				},id);
	}

	public List<String> getBrandList(String sportId) {
		
		if(sportId.equals("0"))
			sportId="%";

		
		return template.query(
				FETCH_BRAND_BY_SPORT_ID,
				(rs, rownum) -> {
					return rs.getString("brand"); 
				},sportId);
	}
	
	public List<ProductWithPrice> getProductWithPrice(String sport_id) {
		List<ProductWithPrice> products = new ArrayList<>();
		String sql = "SELECT a.pid, a.name, b.procurment_price, b.mrp, b.discount_percent FROM product a,profit_marking b "
				+ "WHERE a.sport_id=? AND b.end_date IS NULL AND a.pid=b.pid";
		
		template.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ProductWithPrice product =  new ProductWithPrice(rs.getString("pid"), rs
						.getString("name"), rs.getInt("procurment_price"), rs
						.getInt("mrp"), rs.getInt("discount_percent"));
				products.add(product);
			}
		}, sport_id);
		return products;
	}

}
