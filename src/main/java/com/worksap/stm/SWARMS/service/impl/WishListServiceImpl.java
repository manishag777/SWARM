package com.worksap.stm.SWARMS.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.WishListDao;
import com.worksap.stm.SWARMS.dto.OrderDto;
import com.worksap.stm.SWARMS.dto.WishListDto;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.WishListService;

@Service
public class WishListServiceImpl implements WishListService  {

	@Autowired
	public WishListDao wishListDao;
	
	@Override
	public void insert(WishListDto wishListDto) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			wishListDao.insert(wishListDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
