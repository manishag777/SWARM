package com.worksap.stm.SWARMS.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.entity.ProductSearchFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductSearchFilterEntity;

@Repository
public class TextSearchProductDao {
	
	@Autowired
	private JdbcTemplate template;

	private static final String FETCH_ALL_PRODUCT_FILTERED_ENTITY = "SELECT iurl, qty, info, p.name, pf.margin margin, pd.price cp, pd.discount discount,pd.id pdetail_id ,pd.pid pid, pd.size size, pd.color color, p.name name, p.brand brand, pd.qty qty FROM "
			+ "product_detail pd INNER JOIN product p on pd.pid = p.pid INNER JOIN profit pf on pd.profit_id = pf.id where p.pid LIKE ? and p.name LIKE ? and p.brand LIKE ?";
	
//	private static final String FETCH_ALL_PRODUCT_FILTERED_ENTITY2 = "SELECT iurl, qty, info, p.name, pf.margin margin, pd.price cp, pd.discount discount,pd.id pdetail_id ,pd.pid pid, pd.size size, pd.color color, p.name name, p.brand brand, pd.qty qty FROM "
//			+ "product_detail pd INNER JOIN product p on pd.pid = p.pid INNER JOIN profit pf on pd.profit_id = pf.id where p.pid LIKE ? and p.name LIKE ? and p.brand LIKE ?";
	
	private static String FILTER_ENTITY = "SELECT pd.pid model_no, pd.id id, size, color, p.name name, iurl, brand,info, qty, price, currProfitType profitType, currProfitPercent profitPercent, "
			+ "currDiscountType discountType, currDiscountPercent discountPercent  from "
			+ "product_detail pd inner join product p on pd.pid = p.pid inner join marking m on m.pid = pd.id inner join profit pr on pr.name = currProfitType where brand LIKE ? and  Sport_id LIKE ?"
			+ " and ((price + price*currProfitPercent/100) - (price + price*currProfitPercent/100)*currDiscountPercent/100)"
			+ "between ? and ? and pr.id like ? and currDiscountPercent between ? and 100 and (pd.pid like ? or brand like ? or p.name like ? or size like ? or color like ?)" ;
			
	
	
	
	
	public List<ProductSearchFetchEntity> searchProductByFilter(
			ProductSearchFilterEntity productSearchFilterEntity) {
			
			String sportId = "%", brand = "%", searchText = "%", marginType = "%", priceRange = productSearchFilterEntity.getPriceRange();
			String discountRange = productSearchFilterEntity.getDiscountRange();
			
			String range[] = priceRange.split("-");
			int lowerLimit = Integer.parseInt(range[0]);
			int higherLimit = Integer.parseInt(range[1]);
			System.out.println(lowerLimit + " "+ higherLimit);
			//String pid = "%", brand = "%", name = "%";
			
			if(productSearchFilterEntity.getSportId()!=null && !productSearchFilterEntity.getSportId().equals("0"))
				sportId = productSearchFilterEntity.getSportId();
			
			if(productSearchFilterEntity.getBrand()!=null && !productSearchFilterEntity.getBrand().equals("0"))
				brand = productSearchFilterEntity.getBrand();
			
			if(productSearchFilterEntity.getSearchText()!=null)
				searchText = "%"+productSearchFilterEntity.getSearchText()+"%";
			
			if(productSearchFilterEntity.getMarginType()!=null && !productSearchFilterEntity.getMarginType().equals("0"))
				marginType =productSearchFilterEntity.getMarginType();
			
			
			return template.query(FILTER_ENTITY, new Object[]{brand,sportId,lowerLimit,higherLimit,marginType,discountRange,searchText,searchText,searchText,searchText,searchText}, (rs, column)->{
				ProductSearchFetchEntity product = new 	ProductSearchFetchEntity();
				product.setPdetailId(rs.getInt("id"));
				product.setModelNo(rs.getString("model_no"));
				product.setBrand(rs.getString("brand"));
				product.setColor(rs.getString("color"));
				product.setSize(rs.getString("size"));
				product.setPrice(rs.getInt("price"));
				product.setDiscount(rs.getInt("discountPercent"));
				product.setMargin(rs.getInt("profitPercent"));
				//product.setDiscount(rs.getInt("discount"));
				product.setQty(rs.getInt("qty"));
				product.setInfo(rs.getString("info"));
				product.setName(rs.getString("name"));
				product.setPdetailId(rs.getInt("id"));
				if(rs.getInt("qty")>0)
					product.setIsAvailable("YES");
				else
					product.setIsAvailable("NO");

				product.setUrl(rs.getString("iurl"));
				return product;
				
			});
			

			//System.out.println("pid = "+ pid + "brnad = "+brand+"name = "+name);
			
//			return template.query(
//					FETCH_ALL_PRODUCT_FILTERED_ENTITY ,new Object[]{pid, brand, name},(rs,rownum)->{
//						ProductSearchFetchEntity product = new 	ProductSearchFetchEntity();
//						product.setPid(rs.getString("pid"));
//						product.setBrand(rs.getString("brand"));
//						product.setColor(rs.getString("color"));
//						product.setSize(rs.getString("size"));
//						product.setPrice(rs.getInt("cp"));
//						product.setDiscount(rs.getInt("discount"));
//						product.setQty(rs.getInt("qty"));
//						product.setInfo(rs.getString("info"));
//						product.setName(rs.getString("name"));
//						product.setPdetailId(rs.getInt("pdetail_id"));
//						if(rs.getInt("qty")>0)
//							product.setIsAvailable("YES");
//						else
//							product.setIsAvailable("NO");
//						
//						product.setUrl(rs.getString("iurl"));
//						
//						return product;
//					});
//			}
			
			
			
		

	
	
	}
	
}
