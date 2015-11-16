package com.worksap.stm.SWARMS.service.spec;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.entity.EmployeeFetchEntity;
import com.worksap.stm.SWARMS.entity.EmployeeListEntity;
import com.worksap.stm.SWARMS.entity.ProductFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductFilterEntity;
import com.worksap.stm.SWARMS.entity.ProductProfitEntity;
import com.worksap.stm.SWARMS.entity.ProductSearchFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductSearchFilterEntity;
import com.worksap.stm.SWARMS.exception.ServiceException;


@Service
public interface MyProductService {
	void insert(ProductDto productDto) throws ServiceException;
	void update(ProductDto productDto) throws ServiceException;
	List<ProductDto> getAllProduct(String sport_id) throws ServiceException;
	ProductDto getProductById(String id) throws ServiceException;
	void insertProductDetail(ProductDetailDto productDetailDto) throws ServiceException;
	ProductDetailDto getProductDetail(String pid, String storeId, String size, String color) throws ServiceException;
	List<ProductFetchEntity> getProductListEntity(ProductFilterEntity productFilterEntity ) throws ServiceException;
	void upateProfitMargin(ProductProfitEntity productProfitEntity)  throws ServiceException;
	List<ProductSearchFetchEntity> returnFilteredProducts(
			ProductSearchFilterEntity productSearchFilterEntity);
	
}
