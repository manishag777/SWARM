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
import com.worksap.stm.SWARMS.dto.DiscountDto;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.OrderDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
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
	private static final String FETCH_DISCOUNT_GROUP = "SELECT * FROM discount ";
	private static final String UPDATE_PROFIT_MARGIN = "UPDATE product_detail SET profit_id  = ? WHERE pid = ? and store_id = ? and size = ? and color = ?";
	private static final String UPDATE_QTY = "UPDATE product_detail SET qty  = qty - ? WHERE id = ?";
	private static final String UPDATE_QTY_WQTY_BYID = "UPDATE product_detail SET qty  = qty + ?, warning_qty = ? WHERE id = ?";

	private static final String FETCH_CUSTOMER_LIST_FOR_AVAILABLE_PRODUCT = "SELECT cust_id, firstName, lastName, email FROM product_detail pd INNER JOIN wishlist wl ON pd.id = wl.pd_id"
			+ " INNER JOIN customer c ON c.id = wl.cust_id "
			+ "where pd.pid = ? and store_id = ? and size = ? and color = ?";
	
	private static final String FETCH_PROFIT_MARKING_ENTITY = "SELECT pd.id id, m.* from product_detail pd inner join marking m on pd.id = m.pid ";
	
	
	
	
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
	
	public List<DiscountDto>DiscountDtoList() throws IOException{

		List<DiscountDto> discountDtoList = template.query (FETCH_DISCOUNT_GROUP,
				(rs,rownum) ->{
			return new DiscountDto(rs.getInt("id"), rs.getString("name"), rs.getInt("discount")); 
		});
		
		return discountDtoList;
	}
	
	public List<ProductFetchEntity> ProductFetchEntityList(ProductFilterEntity productFilterEntity) throws IOException {

//
//		List<ProfitDto> profitDtoList = ProductprofitDtoList();
//		String SQL_QUERY;
//		if(productFilterEntity.getGroupType()<=0){
//			 SQL_QUERY = FETCH_ALL_PRODUCT_ENTITY;
//			 return template.query(
//						SQL_QUERY ,(rs,rownum)->{
//							return getProductFetchEntityList(rs,profitDtoList);
//						});
//		}
//		else{
//			
//			SQL_QUERY = FETCH_PRODUCT_ENTITY;
//			return template.query(
//					SQL_QUERY ,(rs,rownum)->{
//						
//						return getProductFetchEntityList(rs,profitDtoList);					
//					},productFilterEntity.getGroupType());
//			}
		return null;
}
		
	public void upateProfitMargin(ProductProfitEntity productProfitEntity) throws IOException {
		// TODO Auto-generated method stub

		template.update(UPDATE_PROFIT_MARGIN, (ps) -> {
			ps.setInt(1, productProfitEntity.getProfit_id());
			ps.setString(2, productProfitEntity.getPid());
			ps.setString(3, "delhi");
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
		//System.out.println("At fetchProductDetailByPid");
		ProductFetchEntity productFetchEntityRes =  template.queryForObject(FETCH_PRODUCT_DETAIL_BYID, (rs,column)->{
			ProductFetchEntity productFetchEntity = new ProductFetchEntity();
			productFetchEntity.setName(rs.getString("name"));
			productFetchEntity.setId(rs.getInt("id"));
			productFetchEntity.setQty(rs.getInt("qty"));
			productFetchEntity.setWqty(rs.getInt("warning_qty"));
			productFetchEntity.setPid(rs.getString("pid"));
			productFetchEntity.setBrand(rs.getString("brand"));
			productFetchEntity.setColor(rs.getString("color"));
			productFetchEntity.setSize(rs.getString("size"));
			System.out.println("Inside query color = " +productFetchEntity.getColor());
			return productFetchEntity;
		},id);
		
		//System.out.println("Inside query color = " +productFetchEntityRes.getColor());
		return productFetchEntityRes;
		
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
						product.setModelNo(rs.getString("pid"));
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
			System.out.println(rs.getInt("id") + " "+ rs.getString("name"));
			return null;
			
		});
		
		return hm;
		
			
		
	}
	
	public List<ProfitMarkingEntity> getProfitMarkingList() throws IOException{

		HashMap<Integer, String> hm = getAllMarking();
		List<ProfitDto> profitDtoList = ProductprofitDtoList();
		List<DiscountDto> discountDtoList = DiscountDtoList();

		return template.query(FETCH_PROFIT_MARKING_ENTITY,(rs,column)->{
			ProfitMarkingEntity profitMarkingEntity = new ProfitMarkingEntity();
			
			profitMarkingEntity.setId(rs.getInt("id"));
			profitMarkingEntity.setPreDate(rs.getString("preDate"));
			profitMarkingEntity.setPreProfitType(rs.getString("preProfitType"));
			profitMarkingEntity.setPreProfitPercent(rs.getInt("preProfitPercent"));
			profitMarkingEntity.setPreDiscountType(rs.getString("preDiscountType"));
			profitMarkingEntity.setPreDiscountPercent(rs.getInt("preDiscountPercent"));
			profitMarkingEntity.setCurrDate(rs.getString("currDate"));
			profitMarkingEntity.setCurrProfitType(rs.getString("currProfitType"));
			profitMarkingEntity.setCurrProfitPercent(rs.getInt("currProfitPercent"));
			profitMarkingEntity.setCurrDiscountType(rs.getString("currDiscountType"));
			profitMarkingEntity.setCurrDiscountPercent(rs.getInt("currDiscountPercent"));
			profitMarkingEntity.setProfitStatus(Utilities.getRandomFloat(-30, +30));
			profitMarkingEntity.setVolumeStatus(Utilities.getRandomFloat(-30, +30));
			String currProfitType = rs.getString("currProfitType");			
			String currDiscountType = rs.getString("currDiscountType");			
			String parameter1 = " \""+rs.getInt("id")+  "\"";
			
			try{
				String s  = "<select onchange='profitFunction(this,"+parameter1+")'>";
				for(int i=0; i<profitDtoList.size(); i++){
					if(currProfitType.equals(profitDtoList.get(i).getName())) {
						s += "<option value="+profitDtoList.get(i).getId()+" selected>"+profitDtoList.get(i).getName() +"</option>" ;
						//System.out.println(profitMarkingEntity.getCurrentMarking()+" "+profitMarkingEntity.getId()+" "+hm.get(selected)+ " "+ profitDtoList.get(i).getId() + " "+ profitDtoList.get(i).getName());
					}
					else
						s += "<option value="+profitDtoList.get(i).getId()+">"+profitDtoList.get(i).getName() +"</option>" ;
				}
				s+= "</select>" ;
				profitMarkingEntity.setProfitSelect(s);
			}
			catch(Exception e){
				
			}
			

			try{
				String s  = "<select onchange='discountFunction(this,"+parameter1+")'>";
				for(int i=0; i<discountDtoList.size(); i++){
					//System.out.println(discountDtoList.get(i).getName());
					if(currDiscountType.equals(discountDtoList.get(i).getName())) {
						s += "<option value="+discountDtoList.get(i).getId()+" selected>"+discountDtoList.get(i).getName() +"</option>" ;
						//System.out.println(profitMarkingEntity.getCurrentMarking()+" "+profitMarkingEntity.getId()+" "+hm.get(selected)+ " "+ profitDtoList.get(i).getId() + " "+ profitDtoList.get(i).getName());
					}
					else
						s += "<option value="+discountDtoList.get(i).getId()+">"+discountDtoList.get(i).getName() +"</option>" ;
				}
				s+= "</select>" ;
				profitMarkingEntity.setDiscountSelect(s);
			}
			catch(Exception e){
				
			}
			
			
			
			
			return profitMarkingEntity;
		});
		
		//return null;
	
	}
	
	public void upateProfitMarkingGroup(String id, String profitId){
		
		String getOld  = "select cmarking, cdate from marking_status where pid = ?";
		String setNew  = "update marking_status SET lmarking = ?, ldate = ?, cmarking = ?, cdate = ? where pid = ?";
		
		template.queryForObject(getOld, (rs,column)->{	
			String cmarking = rs.getString("cmarking");
			String cdate = rs.getString("cdate");
				template.update(setNew, (ps)->{
					ps.setString(1, cmarking);
					ps.setString(2, cdate);
					ps.setString(3, profitId);
					ps.setString(4, Utilities.getCurrentDate());
					ps.setString(5, id);
				});
			return null;
		},id);
		
		
	   
	}
	
public void upateDiscountMarkingGroup(String id, String profitId){
		
		String getOld  = "select currDate, currProfitType, currProfitPercent currDiscounType, currDiscountPercent, cdate from marking_status where pid = ?";
		String setNew  = "update marking_status SET lmarking = ?, ldate = ?, cmarking = ?, cdate = ? where pid = ?";
		
		template.queryForObject(getOld, (rs,column)->{	
			String cmarking = rs.getString("cmarking");
			String cdate = rs.getString("cdate");
				template.update(setNew, (ps)->{
					ps.setString(1, cmarking);
					ps.setString(2, cdate);
					ps.setString(3, profitId);
					ps.setString(4, Utilities.getCurrentDate());
					ps.setString(5, id);
				});
			return null;
		},id);
		
		
}

public ProductSearchFetchEntity getProductDetailByIdAndStore(String id, String storeId) {
	// TODO Auto-generated method stub
	System.out.println(id+ " "+ storeId);
  String sqlQuery = "SELECT pd.pid model_no, pd.id id, size, color, p.name name, brand, qty, price, currProfitPercent profitPercent, "
			+ "currDiscountPercent discountPercent from product_detail pd inner join product p on pd.pid = p.pid inner join marking m on m.pid = pd.id where pd.id = ? and pd.store_id = ?";
//  String mySqlQuery = "SELECT pd.pid model_no, pd.id id, size, color, p.name name, brand, qty, price, currProfitPercent profitPercent, currDiscountPercent discountPercent from product_detail pd inner join product p on pd.pid = p.pid inner join marking m on m.pid = pd.id where pd.id = ?";
  
	//String mySqlQuery2 = "SELECT * FROM product_details where id = ?";
	//System.out.println(mySqlQuery2);
  return template.queryForObject(sqlQuery, new Object[]{id,storeId}, (rs, column)->{
		ProductSearchFetchEntity product = new 	ProductSearchFetchEntity();
		product.setModelNo(rs.getString("model_no"));
		product.setBrand(rs.getString("brand"));
		product.setColor(rs.getString("color"));
		product.setSize(rs.getString("size"));
		product.setPrice(rs.getInt("price"));
		product.setDiscount(rs.getInt("discountPercent"));
		product.setMargin(rs.getInt("profitPercent"));
		//product.setDiscount(rs.getInt("discount"));
		product.setQty(rs.getInt("qty"));
		product.setName(rs.getString("name"));
		product.setPdetailId(rs.getInt("id"));
		if(rs.getInt("qty")>0)
			product.setIsAvailable("YES");
		else
			product.setIsAvailable("NO");
		
		return product;
		
	});
	
}



		
}
