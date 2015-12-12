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
	@RequestMapping(value = "/fetchEventList", method = RequestMethod.GET)
	@ResponseBody
	public List<StallEventDto> fetchEventList(
			@RequestParam("sport") String sport,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) {

		// System.out.println(sport+ " "+store +" "+ fromDate + " "+ toDate);
		// return null;
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

		stallEventDto1.setEventDate("12/29/2015 - 12/29/2015");
		stallEventDto2.setEventDate("12/25/2015 - 12/25/2015");
		stallEventDto3.setEventDate("12/23/2015 - 12/23/2015");
		stallEventDto4.setEventDate("12/22/2015 - 12/22/2015");
		stallEventDto5.setEventDate("12/20/2015 - 12/20/2015");

		stallEventDto1.setSportType("Athletics");
		stallEventDto2.setSportType("Cycling");
		stallEventDto3.setSportType("Atletics");
		stallEventDto4.setSportType("Athletics");
		stallEventDto5.setSportType("Athletics");

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
