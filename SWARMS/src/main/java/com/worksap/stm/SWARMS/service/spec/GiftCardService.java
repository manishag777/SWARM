package com.worksap.stm.SWARMS.service.spec;

import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;
import com.worksap.stm.SWARMS.exception.ServiceException;

@Service
public interface GiftCardService {
	public int insertGiftCard(GiftCardDetailDto giftCardDetailDto ) throws ServiceException;
	public void updateGiftCard(GiftCardDetailDto giftCardDetailDto ) throws ServiceException;
}
