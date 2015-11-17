package com.worksap.stm.SWARMS.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.NotificationDao;
import com.worksap.stm.SWARMS.dao.OrderDao;
import com.worksap.stm.SWARMS.dao.OrderDetailDao;
import com.worksap.stm.SWARMS.dao.ProductDetailDao;
import com.worksap.stm.SWARMS.dto.OrderDetailDto;
import com.worksap.stm.SWARMS.dto.OrderDto;
import com.worksap.stm.SWARMS.entity.ProductFetchEntity;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.service.spec.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDetailDao orderDetailDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ProductDetailDao productDetailDao;
	
	@Autowired
	NotificationDao notificationDao;

	
	@Override
	public int saveOrder(OrderDto orderDto) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return orderDao.insert(orderDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void saveOrderDetailList(List<OrderDetailDto> OrderDetailDtoList, int orderId)
			throws ServiceException {
		
		try {
			 orderDetailDao.insert(OrderDetailDtoList,orderId);
			 for(int i=0; i<OrderDetailDtoList.size(); i++){
				 ProductFetchEntity productFetchEntity = productDetailDao.upateQuantity(OrderDetailDtoList.get(i));
				 if(productFetchEntity.getQty()<=productFetchEntity.getWqty()){
					 String message = productFetchEntity.getName()+"&"+ productFetchEntity.getBrand()+"&"+productFetchEntity.getPid()+"&"+productFetchEntity.getId()+"&"+productFetchEntity.getSize()+"&"+productFetchEntity.getColor();
					 String username = "manish";  //hardcoded
					 notificationDao.insertNotification(username,message);
				 }
				 //System.out.println(productFetchEntity);
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
}
