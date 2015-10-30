package com.worksap.stm.SWARMS.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.worksap.stm.SWARMS.dao.EmployeeDao;
import com.worksap.stm.SWARMS.dao.UserAccountDao;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.UserAccountDto;
import com.worksap.stm.SWARMS.entity.EmployeeAccountCreationEntity;
import com.worksap.stm.SWARMS.entity.EmployeeEntity;
import com.worksap.stm.SWARMS.entity.EmployeeFetchEntity;
import com.worksap.stm.SWARMS.entity.EmployeeListEntity;
import com.worksap.stm.SWARMS.entity.UserAccountCreationEntity;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional
	@Override
	public void insert(EmployeeAccountCreationEntity employeeAccountCreationEntity)
			throws ServiceException {
		
		String userName = employeeAccountCreationEntity.getUsername();
		// Insert user account
		EmployeeDto employee = new EmployeeDto(employeeAccountCreationEntity);
		
		try {
			employeeDao.insert(employee);
		} catch (Exception e) {
			throw new ServiceException("Cannot add user account for userId: "
					+ employeeAccountCreationEntity.getUsername(), e);
		}
	}
	
	@Override
	public EmployeeListEntity getAllEmployeeData(EmployeeFetchEntity entity) throws ServiceException{
		List<EmployeeDto> employeeDtoList = null;
		try {
			 employeeDtoList= employeeDao.getAllEmployeeData();
			//userCountByOfficeId = userDao.getTotalCount(entity.getOfficeId());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException("Employee List is empty",e);
		}
		
		List<EmployeeEntity> entities = Lists.newArrayList();
		
		for(EmployeeDto employeeDto : employeeDtoList  ){
			EmployeeEntity employeeEntity = new EmployeeEntity(employeeDto);
			entities.add(employeeEntity);
		}
		
		return new EmployeeListEntity(entity.getDraw(),3,3,entities);
		
	}
	
	

}
