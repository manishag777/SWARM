package com.worksap.stm.SWARMS.service.spec;

import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.exception.ServiceException;

public interface CustomerService {

	int insert(CustomerDto customerDto)throws ServiceException;
	CustomerDto getCustomerById(int i) throws ServiceException;
	void update (CustomerDto customerDto)throws ServiceException;
	void updateGiftCardId(int custId, int giftCardId)throws ServiceException;
	void updateEmailId(int custId, String emailId) throws ServiceException;
	void updateCustomerIsNotNew(int custId)throws ServiceException;
	void updateGiftCardForReffering(int customerId, int referrerId, GiftCardDetailDto giftCardDetailDto)throws ServiceException;
	
}
