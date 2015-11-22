package com.worksap.stm.SWARMS.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.OrderDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.ProfitDto;
import com.worksap.stm.SWARMS.entity.ProductFilterEntity;
import com.worksap.stm.SWARMS.entity.ProductFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductProfitEntity;
import com.worksap.stm.SWARMS.entity.ProductSearchFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductSearchFilterEntity;
import com.worksap.stm.SWARMS.entity.ProfitMarkingEntity;
import com.worksap.stm.SWARMS.utils.Utilities;

@Repository
public class ProductDetailDao {
	
	@Autowired
	private JdbcTemplate template;
	
	
	
	private static final String INSERT_PRODUCT_DETAIL = "INSERT INTO product_detail "
			+ " (pid, store_id, size, color, price, qty)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String PRODUCT_COUNT = "SELECT COUNT(*) FROM product_detail WHERE pid = ? and store_id = ? and size = ? and color = ?" ;
	
	private static final String FETCH_PRICE = "SELECT price FROM product_detail WHERE pid = ? and store_id = ? and size = ? and color = ?";

	
	private static final String UPDATE_PRODUCT = "UPDATE product_detail SET qty = qty + ?, price = ? WHERE pid = ? and store_id = ? and size = ? and color = ?";
	
	private static final String FETCH_PRODUCT_DETAIL = "SELECT * FROM product_detail pd INNER JOIN profit p ON pd.profit_id = p.id  WHERE pid = ? and store_id = ? and size = ? and color = ? " ;
	
	private static final String FETCH_PRODUCT_DETAIL_BYID = "SELECT pd.*, name, brand FROM product_detail pd inner join product p on pd.pid = p.pid  where pd.id = ?";

	
	private static final String FETCH_PRODUCT_ENTITY = "SELECT pd.profit_id profit_id, pd.pid pid, pd.size size, pd.color color, p.name name, p.brand brand FROM "
			+ "product_detail pd INNER JOIN product p on pd.pid = p.pid where profit_id = ?";
	
	private static final String FETCH_ALL_PRODUCT_ENTITY = "SELECT pd.profit_id profit_id, pd.pid pid, pd.size size, pd.color color, p.name name, p.brand brand FROM "
			+ "product_detail pd INNER JOIN product p on pd.pid = p.pid";
	
	private static final String FETCH_ALL_PRODUCT_FILTERED_ENTITY = "SELECT iurl, qty, info, p.name, pf.margin margin, pd.price cp, pd.discount discount,pd.id pdetail_id ,pd.pid pid, pd.size size, pd.color color, p.name name, p.brand brand, pd.qty qty FROM "
			+ "product_detail pd INNER JOIN product p on pd.pid = p.pid INNER JOIN profit pf on pd.profit_id = pf.id";
	
	private static final String FETCH_MARGIN_GROUP = "SELECT * FROM profit ";
	private static final String UPDATE_PROFIT_MARGIN = "UPDATE product_detail SET profit_id  = ? WHERE pid = ? and store_id = ? and size = ? and color = ?";
	private static final String UPDATE_QTY = "UPDATE product_detail SET qty  = qty - ? WHERE id = ?";
	private static final String UPDATE_QTY_WQTY_BYID = "UPDATE product_detail SET qty  = qty + ?, warning_qty = ? WHERE id = ?";

	private static final String FETCH_CUSTOMER_LIST_FOR_AVAILABLE_PRODUCT = "SELECT cust_id, firstName, lastName, email FROM product_detail pd INNER JOIN wishlist wl ON pd.id = wl.pd_id"
			+ " INNER JOIN customer c ON c.id = wl.cust_id "
			+ "where pd.pid = ? and store_id = ? and size = ? and color = ?";
	
	private static final String FETCH_PROFIT_MARKING_ENTITY = "SELECT pd.id id, ms.* from product_detail pd inner join marking_status ms on pd.id = ms.pid ";
	
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
		String SQL_QUERY;
		if(productFilterEntity.getGroupType()<=0){
			 SQL_QUERY = FETCH_ALL_PRODUCT_ENTITY;
			 return template.query(
						SQL_QUERY ,(rs,rownum)->{
							return getProductFetchEntityList(rs,profitDtoList);
						});
		}
		else{
			
			SQL_QUERY = FETCH_PRODUCT_ENTITY;
			return template.query(
					SQL_QUERY ,(rs,rownum)->{
						
						return getProductFetchEntityList(rs,profitDtoList);					
					},productFilterEntity.getGroupType());
			}
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
	
	public ProductFetchEntity upateQuantity(OrderDetailDto orderDetailDto) throws IOException {

		System.out.println(orderDetailDto);
		
		template.update(UPDATE_QTY, (ps) -> {
			ps.setInt(1, orderDetailDto.getQty());
			ps.setInt(2,orderDetailDto.getPid());
			
		});
		return fetchProductDetailByPid(orderDetailDto.getPid());
		
		
		
	}
	
	public ProductFetchEntity fetchProductDetailByPid(int id) throws IOException {
		
		return template.queryForObject(FETCH_PRODUCT_DETAIL_BYID, (rs,column)->{
			ProductFetchEntity productFetchEntity = new ProductFetchEntity();
			productFetchEntity.setName(rs.getString("name"));
			productFetchEntity.setId(rs.getInt("id"));
			productFetchEntity.setQty(rs.getInt("qty"));
			productFetchEntity.setWqty(rs.getInt("warning_qty"));
			productFetchEntity.setPid(rs.getString("pid"));
			productFetchEntity.setBrand(rs.getString("brand"));
			productFetchEntity.setColor(rs.getString("color"));
			productFetchEntity.setSize(rs.getString("size"));
			return productFetchEntity;
			
		},id);
	}
	
	private ProductFetchEntity getProductFetchEntityList(ResultSet rs, List<ProfitDto> profitDtoList){

		
		try{
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
		}
		catch(Exception e){
			return null;
		}
	}

	public List<ProductSearchFetchEntity> returnFilteredProducts(
			ProductSearchFilterEntity productSearchFilterEntity) {
			
			return template.query(
					FETCH_ALL_PRODUCT_FILTERED_ENTITY ,(rs,rownum)->{
						ProductSearchFetchEntity product = new 	ProductSearchFetchEntity();
						product.setPid(rs.getString("pid"));
						product.setBrand(rs.getString("brand"));
						product.setColor(rs.getString("color"));
						product.setSize(rs.getString("size"));
						product.setPrice(rs.getInt("cp"));
						product.setDiscount(rs.getInt("discount"));
						product.setQty(rs.getInt("qty"));
						product.setInfo(rs.getString("info"));
						product.setName(rs.getString("name"));
						product.setPdetailId(rs.getInt("pdetail_id"));
						if(rs.getInt("qty")>0)
							product.setIsAvailable("YES");
						else
							product.setIsAvailable("NO");
						
						product.setUrl(rs.getString("iurl"));
						
						return product;
					});
			}

	public List<CustomerDto> getListOfCustomerDtoForAvailableProduct(
			ProductDetailDto productDetailDto) {
		// TODO Auto-generated method stub
		System.out.println(productDetailDto);
		return template.query(
				FETCH_CUSTOMER_LIST_FOR_AVAILABLE_PRODUCT ,new Object[]{productDetailDto.getProductId(),productDetailDto.getStoreId(), productDetailDto.getSize(),productDetailDto.getColor()} ,(rs,rownum)->{
					CustomerDto customer = new 	CustomerDto();
					customer.setId(rs.getString("cust_id"));
					customer.setEmailId(rs.getString("email"));
					customer.setFirstName(rs.getString("firstName"));
					customer.setLastName(rs.getString("lastName"));
					return customer;
				});
	}

	public int fetchPrice(String pid, String color, String size, String storeId) {
		// TODO Auto-generated method stub
		try{
			return template.queryForObject(FETCH_PRICE ,new Object[]{pid,storeId,size,color}, Integer.class);
		}
		catch(Exception e){
			return -1;
		}
	}

	public void updateProductQty(int id, int wq, int pq) {

		template.update(UPDATE_QTY_WQTY_BYID, (ps)->{
			ps.setInt(1,pq);
			ps.setInt(2,wq);
			ps.setInt(3,id);
		});
	
	}
	
	public HashMap<Integer,String> getAllMarking(){
		
		String sqlQuery = "select * from profit";
		HashMap<Integer, String> hm = new HashMap<>();
		
		template.query(sqlQuery,(rs,column)->{
			hm.put(rs.getInt("id"), rs.getString("name"));
			return null;
			
		});
		
		return hm;
		
			
		
	}
	
	public List<ProfitMarkingEntity> getProfitMarkingList() throws IOException{
		HashMap<Integer, String> hm = getAllMarking();
		return template.query(FETCH_PROFIT_MARKING_ENTITY,(rs,column)->{
			ProfitMarkingEntity profitMarkingEntity = new ProfitMarkingEntity();

			profitMarkingEntity.setId(rs.getInt("id"));
			profitMarkingEntity.setPreviousDate(rs.getString("ldate"));
			profitMarkingEntity.setPreviousMarking(hm.get(rs.getInt("lmarking")));
			profitMarkingEntity.setCurrentDate(rs.getString("cdate"));
			profitMarkingEntity.setCurrentMarking(hm.get(rs.getInt("cmarking")));
			int selected=rs.getInt(rs.getInt("cmarking")); 
			String parameter1 = " \""+rs.getInt("id")+  "\"";
			
			try{
				List<ProfitDto> profitDtoList = ProductprofitDtoList();
				String s  = "<select onchange='myFunction(this,"+parameter1+")'>";
				
				for(int i=0; i<profitDtoList.size(); i++){
					if(selected == profitDtoList.get(i).getId() )
						s += "<option value="+profitDtoList.get(i).getId()+" selected>"+profitDtoList.get(i).getName() +"</option>" ;
					else
						s += "<option value="+profitDtoList.get(i).getId()+">"+profitDtoList.get(i).getName() +"</option>" ;
				}
				s+= "</select>" ;
				profitMarkingEntity.setMarkingFilter(s);
				
			}
			catch(Exception e){
				
			}
			return profitMarkingEntity;
		});
		
		//return null;
	
	}

		
}
