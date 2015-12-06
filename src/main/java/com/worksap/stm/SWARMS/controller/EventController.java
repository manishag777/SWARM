package com.worksap.stm.SWARMS.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worksap.stm.SWARMS.dao.EventDao;
import com.worksap.stm.SWARMS.dto.EventDto;
import com.worksap.stm.SWARMS.entity.event.FutureEventEntity;
import com.worksap.stm.SWARMS.entity.event.ProductEntity;
import com.worksap.stm.SWARMS.entity.event.FutureEventProductsFetchEntity;
import com.worksap.stm.SWARMS.utils.Utilities;


@Controller
public class EventController {
	
	@Autowired
	EventDao eventDao;
	
	@RequestMapping(value = "/getFutureEventData", method = RequestMethod.POST)
	@ResponseBody
	public FutureEventProductsFetchEntity getFutureEventData(@RequestBody FutureEventEntity futureEventEntity){
		System.out.println(futureEventEntity);
		return returnFutureEntity(futureEventEntity);
		//return new TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}
	
	private FutureEventProductsFetchEntity returnFutureEntity(FutureEventEntity futureEventEntity){

		
		List<ProductEntity> productEntities = new ArrayList<>();
/*		for(int i=1; i<11; i++){
			productEntities.add(new ProductEntity(i, "xhd"+i, "name",100,20));
		}
*/		return new FutureEventProductsFetchEntity(2,2,2,productEntities);
		
	}
	
	@RequestMapping(value = "/saveCustomerTarget", method = RequestMethod.GET)
	@ResponseBody
	public void saveCustomerTarget(@RequestParam("eventId") int eventId, @RequestParam("target") int target){

		
		System.out.println(eventId +" "+ target);
		eventDao.saveCustomerTarget(eventId, target);
		
		//return new TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}
	
	@RequestMapping(value = "/saveProductTarget", method = RequestMethod.GET)
	@ResponseBody
	public void saveRevenueTarget(@RequestParam("eventId") int eventId, @RequestParam("target") int target){

		
		System.out.println(eventId +" "+ target);
		eventDao.saveRevenueTarget(eventId, target);
		
		//return new TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}
	
	@RequestMapping(value = "/saveMailTask", method = RequestMethod.GET)
	@ResponseBody
	public void saveMailTask(@RequestParam("eventId") int eventId){
		System.out.println(eventId);
		eventDao.saveMailTask(eventId);
		//return new TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}
	
	@RequestMapping(value = "/getTop10RelevantProducts", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductEntity> getTop10RelevantProducts(@RequestParam("eventId") int eventId, @RequestParam("customerCount") int customerCount ){
		
		return eventDao.getTop10RelevantProducts(eventId, customerCount);
		
		//return null;
	}
	
	@RequestMapping(value = "/addEvent", method = RequestMethod.POST )
	@ResponseBody
	public EventDto addEvent(@RequestBody EventDto eventDto ){
		
		//List<StoreFetchEntity> storeFetchEntities = customerClusterEntity
		System.out.println(eventDto);
		int partCount = eventDto.getParticipantCount();
		int expectedCustomerVisitCount = (int) (partCount*(Utilities.getRandomFloat(35, 55)))/100;
		int expectedRevenue =  eventDao.getExpectedRevenue(0,expectedCustomerVisitCount); ;
		eventDto.setExpectedRevenue(expectedRevenue);
		eventDto.setExpectedCustomerVisit(expectedCustomerVisitCount);
		
		try {
			 eventDto.setId(eventDao.insert(eventDto));
			return eventDto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
