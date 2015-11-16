package com.worksap.stm.SWARMS.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.GiftCardDetailDao;
import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.GiftCardService;

@Service
public class GiftCardServiceImpl implements GiftCardService {
	
	@Autowired
	public GiftCardDetailDao giftCardDetailDao;
	@Override
	public int insertGiftCard(GiftCardDetailDto giftCardDetailDto)
			throws ServiceException {
		
		try {
			return giftCardDetailDao.insertGiftCard(giftCardDetailDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void updateGiftCard(GiftCardDetailDto giftCardDetailDto)
			throws ServiceException {
		
		try {
			giftCardDetailDao.updateGiftCard(giftCardDetailDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
