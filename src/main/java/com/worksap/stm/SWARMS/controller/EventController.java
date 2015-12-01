package com.worksap.stm.SWARMS.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worksap.stm.SWARMS.entity.TopProductFilterEntity;
import com.worksap.stm.SWARMS.entity.TopReturnEntity;
import com.worksap.stm.SWARMS.entity.event.FutureEventEntity;
import com.worksap.stm.SWARMS.entity.event.ProductEntity;
import com.worksap.stm.SWARMS.entity.event.FutureEventProductsFetchEntity;;


@Controller
public class EventController {
	
	@RequestMapping(value = "/getFutureEventData", method = RequestMethod.POST)
	@ResponseBody
	public FutureEventProductsFetchEntity getFutureEventData(@RequestBody FutureEventEntity futureEventEntity){
		System.out.println(futureEventEntity);
		return returnFutureEntity(futureEventEntity);
		//return new TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}
	
	private FutureEventProductsFetchEntity returnFutureEntity(FutureEventEntity futureEventEntity){
		
		List<ProductEntity> productEntities = new ArrayList<>();
		for(int i=1; i<11; i++){
			productEntities.add(new ProductEntity(i, "xhd"+i, "name",100,20));
		}
		return new FutureEventProductsFetchEntity(2,2,2,productEntities);
		
	}

}
