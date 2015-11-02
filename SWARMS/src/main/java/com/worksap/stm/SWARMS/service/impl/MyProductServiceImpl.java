package com.worksap.stm.SWARMS.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worksap.stm.SWARMS.dao.ProductDao;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.entity.EmployeeAccountCreationEntity;
import com.worksap.stm.SWARMS.entity.EmployeeFetchEntity;
import com.worksap.stm.SWARMS.entity.EmployeeListEntity;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.EmployeeService;
import com.worksap.stm.SWARMS.service.spec.MyProductService;
import com.worksap.stm.SWARMS.service.spec.ProductService;


@Service
public class MyProductServiceImpl implements MyProductService {
	
	@Autowired
	ProductDao productDao;
	
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
	
	


}
