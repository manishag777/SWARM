package com.worksap.stm.SWARMS.service.spec;

import java.util.List;

import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.exception.ServiceException;

public interface EmailService {

	List<CustomerDto> getListOfCustomerDtoForAvailableProduct(ProductDetailDto productDetailDto)
			throws ServiceException;
	
	public void mailing(List<CustomerDto> customerDtoList, ProductDetailDto productDetailDto);
	
	public void mailingForReffering(String subject, String body, String emailId);
}
