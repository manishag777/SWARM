package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.ProfitDto;
import com.worksap.stm.SWARMS.entity.ProductFilterEntity;
import com.worksap.stm.SWARMS.entity.ProductFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductProfitEntity;
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
	
	private static final String FETCH_PRODUCT_ENTITY = "SELECT pd.profit_id profit_id, pd.pid pid, pd.size size, pd.color color, p.name name, p.brand brand FROM "
			+ "product_detail pd INNER JOIN product p on pd.pid = p.pid where profit_id = ?";
	
	private static final String FETCH_MARGIN_GROUP = "SELECT * FROM profit " ;
	private static final String UPDATE_PROFIT_MARGIN = "UPDATE product_detail SET profit_id  = ? WHERE pid = ? and store_id = ? and size = ? and color = ?";

	
//	String sql = "SELECT u.username name, u.password pass, a.role role FROM "+
//		     "user u INNER JOIN user_role a on u.username=a.username WHERE "+
//		     "a.enabled =1 and u.username = ?";
	
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
					productDetailDto.setId(rs.getInt("id"));
					productDetailDto.setQty(rs.getInt("qty"));
					productDetailDto.setMargin(rs.getInt("margin"));
					productDetailDto.setWarningQty(rs.getInt("warning_qty"));
					productDetailDto.setDiscount(rs.getInt("discount"));
					productDetailDto.setPrice(rs.getInt("price"));
					return productDetailDto;
				
				});
		}
	
	public List<ProfitDto>ProductprofitDtoList() throws IOException{
		List<ProfitDto> profitDtoList = template.query (FETCH_MARGIN_GROUP,
				(rs,rownum) ->{
			return new ProfitDto(rs.getInt("id"), rs.getString("name"), rs.getInt("margin")); 
		});
		
		return profitDtoList;
	}
	
	public List<ProductFetchEntity> ProductFetchEntityList(ProductFilterEntity productFilterEntity) throws IOException {
		
		
		List<ProfitDto> profitDtoList = ProductprofitDtoList();
		
		
		return template.query(
				FETCH_PRODUCT_ENTITY ,(rs,rownum)->{
					ProductFetchEntity productFetchEntity = new ProductFetchEntity();
					productFetchEntity.setPid(rs.getString("pid"));
					productFetchEntity.setName(rs.getString("name"));
					productFetchEntity.setType("badminton");
					productFetchEntity.setBrand(rs.getString("brand"));
					productFetchEntity.setColor(rs.getString("color"));
					
					int selected=rs.getInt("profit_id"); 
					
					String parameter1 = " \""+rs.getString("pid")+  "\""; 
					String parameter2 = " \""+rs.getString("color")+  "\""; 
					String parameter3 = " \""+rs.getString("size")+  "\""; 
					String s  = "<select onchange='myFunction(this,"+parameter1+","+parameter2+"," + parameter3+ ")'>";
					
					for(int i=0; i<profitDtoList.size(); i++){
						if(selected == profitDtoList.get(i).getId() )
							s += "<option value="+profitDtoList.get(i).getId()+" selected>"+profitDtoList.get(i).getName() +"</option>" ;
						else
							s += "<option value="+profitDtoList.get(i).getId()+">"+profitDtoList.get(i).getName() +"</option>" ;
					}
					s+= "</select>" ;
					
					productFetchEntity.setSize(rs.getString("size"));
					productFetchEntity.setMargin(s);
					return productFetchEntity;
				},productFilterEntity.getGroupType());
		}

	public void upateProfitMargin(ProductProfitEntity productProfitEntity) throws IOException {
		// TODO Auto-generated method stub

		template.update(UPDATE_PROFIT_MARGIN, (ps) -> {
			ps.setInt(1, productProfitEntity.getProfit_id());
			ps.setString(2, productProfitEntity.getPid());
			ps.setString(3, "ranchi");
			ps.setString(4, productProfitEntity.getSize());
			ps.setString(5, productProfitEntity.getColor());
			
	 });
	}

	
	
}
