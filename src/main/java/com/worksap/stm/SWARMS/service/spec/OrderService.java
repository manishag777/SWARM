package com.worksap.stm.SWARMS.service.spec;

import java.util.List;

import com.worksap.stm.SWARMS.dto.OrderDto;
import com.worksap.stm.SWARMS.exception.ServiceException;
import com.worksap.stm.SWARMS.dto.OrderDetailDto;


public interface OrderService {
	public int saveOrder(OrderDto orderDto) throws ServiceException;
	public void saveOrderDetailList(List<OrderDetailDto> OrderDetailDtoList, int orderId) throws ServiceException;

}
