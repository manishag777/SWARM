package com.worksap.stm.SWARMS.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.CustomerDao;
import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	@Override
	public int insert(CustomerDto customerDto) throws ServiceException {
		try {
			return customerDao.insert(customerDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("At CustomerServiceImpl :" +e);
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public CustomerDto getCustomerById(String id) throws ServiceException {
		// TODO Auto-generated method stub
		
		return customerDao.getCustomerById(id);
	}

	@Override
	public void update(CustomerDto customerDto) throws ServiceException {

		try {
			customerDao.update(customerDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateGiftCardId(int custId, int giftCardId)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			customerDao.updateGiftCardId(custId,giftCardId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
