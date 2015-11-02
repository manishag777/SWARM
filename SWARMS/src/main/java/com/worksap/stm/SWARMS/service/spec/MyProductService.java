package com.worksap.stm.SWARMS.service.spec;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.entity.EmployeeFetchEntity;
import com.worksap.stm.SWARMS.entity.EmployeeListEntity;
import com.worksap.stm.SWARMS.exception.ServiceException;


@Service
public interface MyProductService {
	void insert(ProductDto productDto) throws ServiceException;
	List<ProductDto> getAllProduct() throws ServiceException; 

}
