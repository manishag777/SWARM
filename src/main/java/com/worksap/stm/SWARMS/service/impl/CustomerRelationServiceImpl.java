package com.worksap.stm.SWARMS.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.GiftCardDao;
import com.worksap.stm.SWARMS.dao.GiftCardDetailDao;
import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;
import com.worksap.stm.SWARMS.dto.GiftCardDto;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.CustomerRelationService;


@Service
public class CustomerRelationServiceImpl implements CustomerRelationService {
	
	
	@Autowired
	GiftCardDao giftCardDao ;
	
	@Autowired
	GiftCardDetailDao giftCardDetailDao ;
	
	@Override
	public void updateGiftCard(GiftCardDto giftCardDto) throws ServiceException {
		try {
			giftCardDao.updateGiftCard(giftCardDto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public GiftCardDto fetchGiftCardDetail() throws ServiceException {
		try {
			return giftCardDao.fetchGiftCardDetail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}


	@Override
	public GiftCardDetailDto getGiftCardSpecification(int id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return giftCardDetailDao.GetgiftCardStatusByCustomerId(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
