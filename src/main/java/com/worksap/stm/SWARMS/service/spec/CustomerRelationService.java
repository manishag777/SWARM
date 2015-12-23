package com.worksap.stm.SWARMS.service.spec;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.GiftCardDao;
import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;
import com.worksap.stm.SWARMS.dto.GiftCardDto;
import com.worksap.stm.SWARMS.exception.ServiceException;

@Service
public interface CustomerRelationService {
	
	public void updateGiftCard(GiftCardDto giftCardDto) throws ServiceException;
	public GiftCardDto fetchGiftCardDetail() throws ServiceException;
	public GiftCardDetailDto getGiftCardSpecification(int id) throws ServiceException;
}
