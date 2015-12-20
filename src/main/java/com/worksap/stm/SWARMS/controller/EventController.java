package com.worksap.stm.SWARMS.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worksap.stm.SWARMS.dao.EventDao;
import com.worksap.stm.SWARMS.dao.NotificationDao;
import com.worksap.stm.SWARMS.dao.StallEventDao;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.dto.RecProductDto;
import com.worksap.stm.SWARMS.dto.RecommendedProductDto;
import com.worksap.stm.SWARMS.dto.SchemeDto;
import com.worksap.stm.SWARMS.dto.StallEventDto;
import com.worksap.stm.SWARMS.dto.UserEventDto;
import com.worksap.stm.SWARMS.entity.event.FutureEventEntity;
import com.worksap.stm.SWARMS.entity.event.FutureEventProductsFetchEntity;
import com.worksap.stm.SWARMS.entity.event.ProductEntity;

@Controller
public class EventController {

	@Autowired
	StallEventDao stallEventDao;

	@Autowired
	EventDao eventDao;

	@Autowired
	NotificationDao notificationDao;
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getRecommendedProductDtoList", method = RequestMethod.GET)
	@ResponseBody
	public List<RecommendedProductDto> getRecommendedProductDtoList(@RequestParam("eventId") int eventId, @RequestParam("participationCount") int particpationCount, @RequestParam("sportType") String sportType){
		System.out.println("At getRecommendedProductDtoList");
		List<RecommendedProductDto> recommendedProductDtoList = new ArrayList<>();
		if(sportType.equals("marathon")){
			
			recommendedProductDtoList.add(new RecommendedProductDto("Running shoes", (int)(particpationCount*0.125), null));
			recommendedProductDtoList.add(new RecommendedProductDto("Shocks", (int)(particpationCount*0.275), null));
			recommendedProductDtoList.add(new RecommendedProductDto("Shorts", (int)(particpationCount*0.230), null));
			recommendedProductDtoList.add(new RecommendedProductDto("T-shirt", (int)(particpationCount*0.085), null));
			recommendedProductDtoList.add(new RecommendedProductDto("Jacket", (int)(particpationCount*0.090), null));
			recommendedProductDtoList.add(new RecommendedProductDto("Sport drinks", (int)(particpationCount*0.430), null));
		}
		else{
			recommendedProductDtoList.add(new RecommendedProductDto("Cycle", (int)(particpationCount*0.0435),null));
			recommendedProductDtoList.add(new RecommendedProductDto("Gloves", (int)(particpationCount*0.123),null));
			recommendedProductDtoList.add(new RecommendedProductDto("Helmet", (int)(particpationCount*0.067), null));
			recommendedProductDtoList.add(new RecommendedProductDto("Jacket", (int)(particpationCount*0.07), null) );
			recommendedProductDtoList.add(new RecommendedProductDto("Air pump", (int)(particpationCount*0.04), null));
			recommendedProductDtoList.add(new RecommendedProductDto("sport drinks", (int)(particpationCount*0.330), null));
		}
		return recommendedProductDtoList;
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getRecommendedProductDetailDtoList", method = RequestMethod.GET)
	@ResponseBody
	public List<RecommendedProductDto> getRecommendedProductDetailDtoList(@RequestParam("eventId") int eventId, @RequestParam("participationCount") int particpationCount, @RequestParam("sportType") String sportType){
		System.out.println("At getRecommendedProductDtoList");
		if(sportType.equals("marathon")){
			List<RecommendedProductDto> recommendedProductDtoList = new ArrayList<>();
			List<ProductEntity> productDtoList = new ArrayList<>();
			ProductEntity productEntity1 = new ProductEntity();
			ProductEntity productEntity2 = new ProductEntity();
			ProductEntity productEntity3 = new ProductEntity();
			ProductEntity productEntity4 = new ProductEntity();
			productEntity1.setModelNo("Z1JG79GR22");
			productEntity1.setName("Vector X Athletix 001 Running Shoes");
			
			productEntity2.setModelNo("X1YG79LP22");
			productEntity2.setName("Puma shiny Running Shoes");
			
			productEntity3.setModelNo("AKJL79GR22");
			productEntity3.setName("Rebook Running Shoes");
			
			productEntity4.setModelNo("LTJL79GR90");
			productEntity4.setName("Addidas fast Running Shoes");
			
			
			int totalShoes = (int) (particpationCount*0.125) ;
			productEntity1.setExpectedSales((totalShoes*39)/100);
			productEntity2.setExpectedSales((totalShoes*28)/100);
			productEntity3.setExpectedSales((totalShoes*22)/100);
			productEntity4.setExpectedSales((totalShoes*11)/100);
			
			productEntity1.setAvailableQty((totalShoes*29)/100);
			productEntity2.setAvailableQty((totalShoes*18)/100);
			productEntity3.setAvailableQty((totalShoes*12)/100);
			productEntity4.setAvailableQty((totalShoes*5)/100);
			
			productEntity1.setRemainingSales((totalShoes*39)/100 - (totalShoes*29)/100 );
			productEntity2.setRemainingSales((totalShoes*28)/100 - (totalShoes*18)/100 );
			productEntity3.setRemainingSales((totalShoes*22)/100 - (totalShoes*12)/100 );
			productEntity4.setRemainingSales((totalShoes*11)/100 - (totalShoes*5)/100 );
			
			
			productDtoList.add(productEntity1);
			productDtoList.add(productEntity2);
			productDtoList.add(productEntity3);
			productDtoList.add(productEntity4);
			
			recommendedProductDtoList.add(new RecommendedProductDto("Running shoes", (int)(particpationCount*0.125),productDtoList));
			recommendedProductDtoList.add(new RecommendedProductDto("Shocks", (int)(particpationCount*0.275),productDtoList ));
			recommendedProductDtoList.add(new RecommendedProductDto("Shorts", (int)(particpationCount*0.230), productDtoList));
			recommendedProductDtoList.add(new RecommendedProductDto("T-shirt", (int)(particpationCount*0.085), productDtoList) );
			recommendedProductDtoList.add(new RecommendedProductDto("Jacket", (int)(particpationCount*0.090), productDtoList));
			recommendedProductDtoList.add(new RecommendedProductDto("sport drinks", (int)(particpationCount*0.430), productDtoList));
			return recommendedProductDtoList;
		}
		
		else{
			List<RecommendedProductDto> recommendedProductDtoList = new ArrayList<>();
			List<ProductEntity> productDtoList = new ArrayList<>();
			ProductEntity productEntity1 = new ProductEntity();
			ProductEntity productEntity2 = new ProductEntity();
			ProductEntity productEntity3 = new ProductEntity();
			ProductEntity productEntity4 = new ProductEntity();
			
			productEntity1.setModelNo("AXDFGRVG");
			productEntity1.setName("Hero Megastar 26T 18Speed Road Cycle");
			
			productEntity2.setModelNo("79LP22XABV");
			productEntity2.setName("Hero Studd 26T 18 Speed Sprint Cycle");
			
			productEntity3.setModelNo("AKJL79GR22");
			productEntity3.setName("Hero Cycles Ranger Dtb Vx 6 Speed Cycle");
			
			productEntity4.setModelNo("LTJL79GR90");
			productEntity4.setName("Hercules Roadeo A100 Vx 21 Speed Bicycle");
			
			
			int totalShoes = (int) (particpationCount*0.0435) ;
			productEntity1.setExpectedSales((totalShoes*39)/100);
			productEntity2.setExpectedSales((totalShoes*28)/100);
			productEntity3.setExpectedSales((totalShoes*22)/100);
			productEntity4.setExpectedSales((totalShoes*11)/100);
			
			productEntity1.setAvailableQty((totalShoes*29)/100);
			productEntity2.setAvailableQty((totalShoes*18)/100);
			productEntity3.setAvailableQty((totalShoes*12)/100);
			productEntity4.setAvailableQty((totalShoes*5)/100);
			
			productEntity1.setRemainingSales((totalShoes*39)/100 - (totalShoes*29)/100 );
			productEntity2.setRemainingSales((totalShoes*28)/100 - (totalShoes*18)/100 );
			productEntity3.setRemainingSales((totalShoes*22)/100 - (totalShoes*12)/100 );
			productEntity4.setRemainingSales((totalShoes*11)/100 - (totalShoes*5)/100 );
			
			
			productDtoList.add(productEntity1);
			productDtoList.add(productEntity2);
			productDtoList.add(productEntity3);
			productDtoList.add(productEntity4);
			
			recommendedProductDtoList.add(new RecommendedProductDto("Cycle", (int)(particpationCount*0.0435),productDtoList));
			recommendedProductDtoList.add(new RecommendedProductDto("Gloves", (int)(particpationCount*0.123),productDtoList ));
			recommendedProductDtoList.add(new RecommendedProductDto("Helmet", (int)(particpationCount*0.067), productDtoList));
			recommendedProductDtoList.add(new RecommendedProductDto("Jacket", (int)(particpationCount*0.07), productDtoList) );
			recommendedProductDtoList.add(new RecommendedProductDto("Air pump", (int)(particpationCount*0.04), productDtoList));
			recommendedProductDtoList.add(new RecommendedProductDto("sport drinks", (int)(particpationCount*0.330), productDtoList));
			return recommendedProductDtoList;
		}
	
	}
		
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getOfferSchemesList", method = RequestMethod.GET)
	@ResponseBody
	public List<List<SchemeDto>> getOfferSchemesList(@RequestParam("eventId") int eventId, String sportType, String offerType){
		
		System.out.println(offerType);
		if(sportType.equals("marathon")){
			if(offerType.equals("preEvent")){
				List<SchemeDto> recommendedSchemeList = new ArrayList<>();
				recommendedSchemeList.add(new SchemeDto(1, "15% off. on all marathon accessories" ));
				recommendedSchemeList.add(new SchemeDto(2, "20% off. on all marathon kit bag" ));
				List<SchemeDto> otherSchemeList = new ArrayList<>();
				otherSchemeList.add(new SchemeDto(3, "10% off. on all running shoes" ));
				otherSchemeList.add(new SchemeDto(4, "15% off. on all marathon t-shirt" ));
				otherSchemeList.add(new SchemeDto(5, "15% off. on puma, rebook sweat-shirt" ));
				List<List<SchemeDto>> res = new ArrayList<>();
				res.add(recommendedSchemeList);
				res.add(otherSchemeList);
				return res;
			}
			else{
				List<SchemeDto> recommendedSchemeList = new ArrayList<>();
				recommendedSchemeList.add(new SchemeDto(1, "15% off. on all sportswear"));
				List<SchemeDto> otherSchemeList = new ArrayList<>();
				otherSchemeList.add(new SchemeDto(3, "10% off. on all running shoes" ));
				otherSchemeList.add(new SchemeDto(4, "15% off. on all marathon t-shirt" ));
				otherSchemeList.add(new SchemeDto(5, "15% off. on puma, rebook sweat-shirt" ));
				otherSchemeList.add(new SchemeDto(1, "15% off. on all marathon accessories" ));
				List<List<SchemeDto>> res = new ArrayList<>();
				res.add(recommendedSchemeList);
				res.add(otherSchemeList);
				return res;
			}
		}
		else{
			if(offerType.equals("preEvent")){
				List<SchemeDto> recommendedSchemeList = new ArrayList<>();
				recommendedSchemeList.add(new SchemeDto(1, "15% off. on all cycling accessories" ));
				recommendedSchemeList.add(new SchemeDto(2, "20% off. on all cycling kit bag" ));
				List<SchemeDto> otherSchemeList = new ArrayList<>();
				otherSchemeList.add(new SchemeDto(3, "15% off. on all cycle" ));
				otherSchemeList.add(new SchemeDto(4, "10% off. on all cycling jacket" ));
				otherSchemeList.add(new SchemeDto(5, "10% off. on all branded gloves "));
				List<List<SchemeDto>> res = new ArrayList<>();
				res.add(recommendedSchemeList);
				res.add(otherSchemeList);
				return res;
			}
			else{
				List<SchemeDto> recommendedSchemeList = new ArrayList<>();
				recommendedSchemeList.add(new SchemeDto(1, "15% off. on all sports wear" ));
				List<SchemeDto> otherSchemeList = new ArrayList<>();
				otherSchemeList.add(new SchemeDto(3, "10% off. on all cycle" ));
				otherSchemeList.add(new SchemeDto(4, "10% off. on all cycling jacket" ));
				otherSchemeList.add(new SchemeDto(5, "10% off. on all branded gloves "));
				otherSchemeList.add(new SchemeDto(2, "10% off. on all cycling kit bag" ));
				List<List<SchemeDto>> res = new ArrayList<>();
				res.add(recommendedSchemeList);
				res.add(otherSchemeList);
				return res;
			}
			
		}
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/updateManagerNotification", method = RequestMethod.GET)
	@ResponseBody
	public void updateManagerNotification(@RequestParam("eventId") int eventId){
		stallEventDao.updateManagerNotification(eventId);
	}
	
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/updateManagerTimelineProgress", method = RequestMethod.GET)
	@ResponseBody
	public void updateManagerTimelineProgress(@RequestParam("eventId") int eventId){
		stallEventDao.updateManagerTimelineProgress(eventId);
	}
	
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/fetchEventNotification", method = RequestMethod.GET)
	@ResponseBody
	public List<StallEventDto> fetchEventNotification(){
		return stallEventDao.fetchEventNotification();
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/updateTheScheme", method = RequestMethod.GET)
	@ResponseBody
	public void updateTheScheme(@RequestParam("eventId") int eventId, @RequestParam("type") String type ){
		stallEventDao.updateTheScheme(eventId, type);
	}

		
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/fetchEventList", method = RequestMethod.GET)
	@ResponseBody
	public List<StallEventDto> fetchEventList(
			@RequestParam("sport") String sport,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {


		List<StallEventDto> res = stallEventDao.fetchStallEventDto(sport,
				fromDate, toDate);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i).getId());
			List<String> userList = stallEventDao.fetchUsersList(res.get(i)
					.getId());
			res.get(i).setUserList(userList);
		}
		return res;

	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/fetchEventStarterList", method = RequestMethod.GET)
	@ResponseBody
	public List<StallEventDto> fetchEventStarterList() {


		List<StallEventDto> stallEventDtoList = new ArrayList<>();

		StallEventDto stallEventDto1 = new StallEventDto();
		StallEventDto stallEventDto2 = new StallEventDto();
		StallEventDto stallEventDto3 = new StallEventDto();
		StallEventDto stallEventDto4 = new StallEventDto();
		StallEventDto stallEventDto5 = new StallEventDto();

		stallEventDto1.setId(1);
		stallEventDto2.setId(2);
		stallEventDto3.setId(3);
		stallEventDto4.setId(4);
		stallEventDto5.setId(5);

		stallEventDto1.setIsAdded(0);
		stallEventDto1.setIsSeen(0);

		stallEventDto2.setIsAdded(0);
		stallEventDto2.setIsSeen(0);

		stallEventDto3.setIsAdded(0);
		stallEventDto3.setIsSeen(1);

		stallEventDto4.setIsAdded(1);
		stallEventDto4.setIsSeen(1);

		stallEventDto5.setIsAdded(0);
		stallEventDto5.setIsSeen(1);

		stallEventDto1.setEventName("Sunfire Run 10K  New Delhi DL");
		stallEventDto2.setEventName("de Delhi Tour Cycling Delhi DL");
		stallEventDto3.setEventName("Golden Jubilee Half New Delhi DL");
		stallEventDto4.setEventName("Freedom Run and Ride Noida UP");
		stallEventDto5.setEventName("Run to Feed - New Delhi New Delhi DL");
		
		stallEventDto1.setRelevanceFactor(8);
		stallEventDto2.setRelevanceFactor(6);
		stallEventDto3.setRelevanceFactor(4);
		stallEventDto4.setRelevanceFactor(9);
		stallEventDto5.setRelevanceFactor(5);
		
		stallEventDto1.setDetail("A Pleasant morning with a bit of fog. We are hosting CARDIO FITNESS RUN at the core of Delhi. All participants of 12 Km will get T-shirt, finisher medals, certificate, and refreshment. The participants of 6 Km will get finisher medal, participation certificate and refreshment. Timing Certificates will be emailed or given on the Spot.");
		stallEventDto2.setDetail("The Sportive Ride and The Straits Times Ride both , starting at the Delhi Sports Hub and ending inside the National Stadium. Cyclists of The Sportive Ride also took a short ‘journey to the West’ via the Keppel viaduct.");

		
		stallEventDto1.setEventDate("12/29/2015 - 12/29/2015");
		stallEventDto2.setEventDate("12/25/2015 - 12/25/2015");
		stallEventDto3.setEventDate("12/23/2015 - 12/23/2015");
		stallEventDto4.setEventDate("12/22/2015 - 12/22/2015");
		stallEventDto5.setEventDate("12/20/2015 - 12/20/2015");

		stallEventDto1.setSportType("marathon");
		stallEventDto2.setSportType("Cycling");
		stallEventDto3.setSportType("marathon");
		stallEventDto4.setSportType("marathon");
		stallEventDto5.setSportType("marathon");

		stallEventDto1.setCoName("Rakesh Malhotra");
		stallEventDto2.setCoName("Shivam jain");
		stallEventDto3.setCoName("Nikhil Khandelwal");
		stallEventDto4.setCoName("Lavish khanna");
		stallEventDto5.setCoName("Prabahat bajpai");

		stallEventDto1.setCoPhone("+91-8800837388");
		stallEventDto2.setCoPhone("+91-9800837388");
		stallEventDto3.setCoPhone("+91-9900838873");
		stallEventDto4.setCoPhone("+91-8823837388");
		stallEventDto5.setCoPhone("+91-8821837388");

		stallEventDto1.setCoEmail("rmalhotra@gmail.com");
		stallEventDto2.setCoEmail("shivamJain@gmail.com");
		stallEventDto3.setCoEmail("nikhilkan@gmail.com");
		stallEventDto4.setCoEmail("lavKhanna23@gmail.com");
		stallEventDto5.setCoEmail("prabhatkhabar@gmail.com");
		
		stallEventDto1.setPlaceEvent("Connaught place delhi - 110015");
		stallEventDto2.setPlaceEvent("Rajiv chowk delhi - 110008");
		stallEventDto3.setPlaceEvent("India gate delhi - 110002");
		stallEventDto4.setPlaceEvent("Huda city centre noida - 140026");
		stallEventDto5.setPlaceEvent("Hauz khas delhi - 110016");
		
		stallEventDtoList.add(stallEventDto1);
		stallEventDtoList.add(stallEventDto2);
		stallEventDtoList.add(stallEventDto3);
		stallEventDtoList.add(stallEventDto4);
		stallEventDtoList.add(stallEventDto5);

		return stallEventDtoList;
	}

	@RequestMapping(value = "/updateCmStatus", method = RequestMethod.GET)
	@ResponseBody
	public void updateCmStatus(@RequestParam("eventId") int eventId,
			@RequestParam("value") int value) {

		System.out.println(eventId + " " + value);
		stallEventDao.updateCmStatus(eventId, value);

		// return new
		// TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}

	@RequestMapping(value = "/updateMStatus", method = RequestMethod.GET)
	@ResponseBody
	public void updateMStatus(@RequestParam("eventId") int eventId,
			@RequestParam("value") int value) {

		System.out.println(eventId + " " + value);
		stallEventDao.updateMStatus(eventId, value);

		// return new
		// TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}

	@RequestMapping(value = "/upDateMeetingTime", method = RequestMethod.GET)
	@ResponseBody
	public void upDateMeetingTime(@RequestParam("eventId") int eventId,
			@RequestParam("dateTime") String dateTime) {

		System.out.println(eventId + " " + dateTime);
		stallEventDao.upDateMeetingTime(eventId, dateTime);

		// return new
		// TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}
	
	@RequestMapping(value = "/saveMeetingParam", method = RequestMethod.GET)
	@ResponseBody
	public void saveMeetingParam(@RequestParam("eventId") int eventId,
			@RequestParam("stallCount") int stallCount, @RequestParam("participantCount") int participantcount,
			@RequestParam("fees") int fees) {

		System.out.println(eventId + " " + stallCount + " " + fees + " "+participantcount);
		stallEventDao.saveMeetingParam(eventId, stallCount, participantcount, fees);
	}

	@RequestMapping(value = "/saveFeesAndStallCount", method = RequestMethod.GET)
	@ResponseBody
	public void saveFeesAndStallCount(@RequestParam("eventId") int eventId,
			@RequestParam("stallCount") int stallCount,
			@RequestParam("fees") int fees) {

		System.out.println(eventId + " " + stallCount + " " + fees);
		stallEventDao.saveFeesAndStallCount(eventId, stallCount, fees);

		// return new
		// TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}

	@RequestMapping(value = "/saveAssignedUser", method = RequestMethod.POST)
	@ResponseBody
	public void saveAssignedUser(@RequestBody UserEventDto userEventDto) {

		System.out.println(userEventDto);
		stallEventDao.saveAssignedUser(userEventDto);
		for (int i = 0; i < userEventDto.getUser().size(); i++) {
			notificationDao.insertNotification(userEventDto.getUser().get(i),
					"Event Task Assigned");
			notificationDao.insertNotification(
					userEventDto.getUser().get(i),
					"Meeting for event training at "
							+ userEventDto.getTrainingTime());
		}
		// return new
		// TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}

	@RequestMapping(value = "/getFutureEventData", method = RequestMethod.POST)
	@ResponseBody
	public FutureEventProductsFetchEntity getFutureEventData(
			@RequestBody FutureEventEntity futureEventEntity) {
		System.out.println(futureEventEntity);
		return returnFutureEntity(futureEventEntity);
		// return new
		// TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}

	private FutureEventProductsFetchEntity returnFutureEntity(
			FutureEventEntity futureEventEntity) {

		List<ProductEntity> productEntities = new ArrayList<>();
		/*
		 * for(int i=1; i<11; i++){ productEntities.add(new ProductEntity(i,
		 * "xhd"+i, "name",100,20)); }
		 */return new FutureEventProductsFetchEntity(2, 2, 2, productEntities);

	}

	@RequestMapping(value = "/saveCustomerTarget", method = RequestMethod.GET)
	@ResponseBody
	public void saveCustomerTarget(@RequestParam("eventId") int eventId,
			@RequestParam("target") int target) {

		System.out.println(eventId + " " + target);
		eventDao.saveCustomerTarget(eventId, target);

		// return new
		// TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}

	@RequestMapping(value = "/saveProductTarget", method = RequestMethod.GET)
	@ResponseBody
	public void saveRevenueTarget(@RequestParam("eventId") int eventId,
			@RequestParam("target") int target) {

		System.out.println(eventId + " " + target);
		eventDao.saveRevenueTarget(eventId, target);

		// return new
		// TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}

	@RequestMapping(value = "/saveMailTask", method = RequestMethod.GET)
	@ResponseBody
	public void saveMailTask(@RequestParam("eventId") int eventId) {
		System.out.println(eventId);
		eventDao.saveMailTask(eventId);
		// return new
		// TopReturnEntity(2,2,2,eventDao.getTopProductsData(topProductFilterEntity.getEventId()));
	}

	@RequestMapping(value = "/getTop10RelevantProducts", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductEntity> getTop10RelevantProducts(
			@RequestParam("eventId") int eventId,
			@RequestParam("customerCount") int customerCount) {

		return eventDao.getTop10RelevantProducts(eventId, customerCount);

		// return null;
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	@ResponseBody
	public StallEventDto addEvent(@RequestBody StallEventDto eventDto) {

		System.out.println(eventDto);
		try {
			eventDto.setId(stallEventDao.insert(eventDto));
			return eventDto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
