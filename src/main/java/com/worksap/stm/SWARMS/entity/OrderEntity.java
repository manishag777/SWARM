package com.worksap.stm.SWARMS.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;
import com.worksap.stm.SWARMS.dto.GiftCardDto;
import com.worksap.stm.SWARMS.dto.OrderDetailDto;
import com.worksap.stm.SWARMS.dto.OrderDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderEntity {
	OrderDto orderDto;
	GiftCardDetailDto giftCardDetailDto;
	List<OrderDetailDto>  orderDetailDtoList;
}
