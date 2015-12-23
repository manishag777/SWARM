package com.worksap.stm.SWARMS.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.CustomerDao;
import com.worksap.stm.SWARMS.dao.GiftCardDetailDao;
import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.CustomerService;
import com.worksap.stm.SWARMS.service.spec.EmailService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private GiftCardDetailDao giftCardDetailDao;
	
	@Autowired
	private EmailService emailService;
	
	
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
	public CustomerDto getCustomerById(int id) throws ServiceException {
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

	

	@Override
	public void updateEmailId(int custId, String emailId)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			customerDao.updateEmailId(custId, emailId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCustomerIsNotNew(int custId) throws ServiceException {
		// TODO Auto-generated method stub
				try {
					customerDao.updateCustomerIsNotNew(custId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	@Override
	public void updateGiftCardForReffering(int customerId, int referrerId,
			GiftCardDetailDto giftCardDetailDto) throws ServiceException {
		
		

		// TODO Auto-generated method stub
		try {
			CustomerDto customerDto = customerDao.getCustomerById(customerId);
			CustomerDto referrerDto = customerDao.getCustomerById(referrerId);
			String subject = "", body = "", emailId = referrerDto.getEmailId();
			
			GiftCardDetailDto referreGiftCardDetailDto  = giftCardDetailDao.GetgiftCardStatusByCustomerId(referrerId);
			if(referreGiftCardDetailDto ==null){
				System.out.println("No existing Gift-card for Referral");
				//giftCardDetailDto.ge
				
				int giftCardId = giftCardDetailDao.insertGiftCard(giftCardDetailDto);
				customerDao.updateGiftCardId(referrerId,giftCardId);
				subject = "Gift-card has been awarded";
				body = "Dear customer, Gift-card has been awarded.";
				
				
			}
			else{
				giftCardDetailDto.setId(referreGiftCardDetailDto.getId());
				giftCardDetailDao.updateGiftCard(giftCardDetailDto);
				subject = "Extra balance has been added in your gif-card";
				body = "Dear customer,Extra balance has been added in your gif-card";
			}
			
			if(emailId!=null)
				emailService.mailingForReffering(subject, body, emailId);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
