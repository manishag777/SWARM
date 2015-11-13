package com.worksap.stm.SWARMS.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksap.stm.SWARMS.dao.ProductDao;
import com.worksap.stm.SWARMS.dao.ProductDetailDao;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.entity.EmployeeAccountCreationEntity;
import com.worksap.stm.SWARMS.entity.EmployeeFetchEntity;
import com.worksap.stm.SWARMS.entity.EmployeeListEntity;
import com.worksap.stm.SWARMS.entity.ProductFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductFilterEntity;
import com.worksap.stm.SWARMS.entity.ProductProfitEntity;
import com.worksap.stm.SWARMS.entity.ProductSearchFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductSearchFilterEntity;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.EmployeeService;
import com.worksap.stm.SWARMS.service.spec.MyProductService;
import com.worksap.stm.SWARMS.service.spec.ProductService;


@Service
public class MyProductServiceImpl implements MyProductService {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductDetailDao productDetailDao;
	
	@Transactional
	@Override
	public void insert(ProductDto product) throws ServiceException {
		
		try {
			productDao.insert(product);
			
			
		} catch (Exception e) {
			throw new ServiceException("Cannot add user account for userId: "
					+ product.getProductId(), e);
		}
				
	}


	@Override
	public List<ProductDto> getAllProduct() throws ServiceException {
		// TODO Auto-generated method stub
		return productDao.getAllProduct();
		
	}


	@Override
	public void insertProductDetail(ProductDetailDto productDetailDto)
			throws ServiceException {
		// TODO Auto-generated method stub
		
		try {
			productDetailDao.update(productDetailDto);
			
			
		} catch (Exception e) {
			throw new ServiceException("Cannot add user account for userId: "
					+ productDetailDto.getProductId(), e);
		}
		
		
	}


	@Override
	public void update(ProductDto productDto) throws ServiceException {
		
		try {
			productDao.update(productDto);		
		} catch (Exception e) {
			throw new ServiceException("Cannot add user account for userId: "
					+ productDto.getProductId(), e);
		}
	}


	@Override
	public ProductDto getProductById(String id) throws ServiceException {
		
		return productDao.getProductById(id);	
}


	@Override
	public ProductDetailDto getProductDetail(String pid, String storeId,
			String size, String color) throws ServiceException {

		try {
			return productDetailDao.getProductDetail(pid,storeId,size,color);
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<ProductFetchEntity> getProductListEntity(
			ProductFilterEntity productFilterEntity) throws ServiceException {
		try {
			return productDetailDao.ProductFetchEntityList(productFilterEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}


	@Override
	public void upateProfitMargin(ProductProfitEntity productProfitEntity)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			productDetailDao.upateProfitMargin(productProfitEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public List<ProductSearchFetchEntity> returnFilteredProducts(
			ProductSearchFilterEntity productSearchFilterEntity) {
		
		return productDetailDao.returnFilteredProducts(productSearchFilterEntity);
	}
	
	


}
