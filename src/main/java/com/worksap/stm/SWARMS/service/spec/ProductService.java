package com.worksap.stm.SWARMS.service.spec;

import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.entity.EmployeeAccountCreationEntity;
import com.worksap.stm.SWARMS.entity.EmployeeFetchEntity;
import com.worksap.stm.SWARMS.entity.EmployeeListEntity;
import com.worksap.stm.SWARMS.exception.ServiceException;

@Service
public interface ProductService {
	
	void insert(EmployeeAccountCreationEntity employeeAccountCreationEntity)
			throws ServiceException;
	
	EmployeeListEntity getAllEmployeeData(EmployeeFetchEntity entity) throws ServiceException;
	
}
