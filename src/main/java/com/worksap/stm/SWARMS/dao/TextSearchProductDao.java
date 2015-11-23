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
	
	private static final String FETCH_ALL_PRODUCT_FILTERED_ENTITY2 = "SELECT iurl, qty, info, p.name, pf.margin margin, pd.price cp, pd.discount discount,pd.id pdetail_id ,pd.pid pid, pd.size size, pd.color color, p.name name, p.brand brand, pd.qty qty FROM "
			+ "product_detail pd INNER JOIN product p on pd.pid = p.pid INNER JOIN profit pf on pd.profit_id = pf.id where p.pid LIKE ? and p.name LIKE ? and p.brand LIKE ?";
	
	
	
	
	public List<ProductSearchFetchEntity> searchProductByFilter(
			ProductSearchFilterEntity productSearchFilterEntity) {
			
			String sportId = "%", brand = "%", searchText = "%", marginType = "%";
			//String pid = "%", brand = "%", name = "%";
			
			if(!productSearchFilterEntity.getSportId().equals("0"))
				sportId = productSearchFilterEntity.getSportId();
			
			if(!productSearchFilterEntity.getBrand().equals("0"))
				brand = productSearchFilterEntity.getBrand();
			
			if(productSearchFilterEntity.getSearchText()!=null)
				searchText = "%"+productSearchFilterEntity.getSearchText()+"%";
			
			if(!productSearchFilterEntity.getMarginType().equals("0"))
				marginType =productSearchFilterEntity.getMarginType();

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
			
			
			
			return null;

	
	
	}
	
}
