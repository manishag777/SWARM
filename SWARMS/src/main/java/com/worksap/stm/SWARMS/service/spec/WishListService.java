package com.worksap.stm.SWARMS.service.spec;

import com.worksap.stm.SWARMS.dto.WishListDto;
import com.worksap.stm.SWARMS.exception.ServiceException;

public interface WishListService {
	public void insert(WishListDto wishListDto) throws ServiceException;
}
