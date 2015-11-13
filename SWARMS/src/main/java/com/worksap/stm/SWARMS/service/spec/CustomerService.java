package com.worksap.stm.SWARMS.service.spec;

import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.exception.ServiceException;

public interface CustomerService {

	int insert(CustomerDto customerDto)throws ServiceException;
	CustomerDto getCustomerById(String id) throws ServiceException;
	void update (CustomerDto customerDto)throws ServiceException;
	void updateGiftCardId(int custId, int giftCardId)throws ServiceException;
	void updateEmailId(int custId, String emailId) throws ServiceException;
	
}
