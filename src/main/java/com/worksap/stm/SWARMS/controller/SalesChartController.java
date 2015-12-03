package com.worksap.stm.SWARMS.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worksap.stm.SWARMS.dao.SportDao;
import com.worksap.stm.SWARMS.dao.StoreDao;
import com.worksap.stm.SWARMS.dao.analysis.CustomerAnalysisDao;
import com.worksap.stm.SWARMS.dao.analysis.RevenueSalesChartDao;
import com.worksap.stm.SWARMS.dto.SportDto;
import com.worksap.stm.SWARMS.dto.StoreDto;
import com.worksap.stm.SWARMS.dto.analysis.CustomerDto;
import com.worksap.stm.SWARMS.dto.analysis.RevenueDto;
import com.worksap.stm.SWARMS.entity.analysis.CustomerReturnEntity;
import com.worksap.stm.SWARMS.entity.analysis.SalesChartGetEntity;
import com.worksap.stm.SWARMS.entity.analysis.SalesChartReturnEntity;
import com.worksap.stm.SWARMS.entity.event.FutureEventEntity;
import com.worksap.stm.SWARMS.entity.event.FutureEventProductsFetchEntity;

@Controller
public class SalesChartController {
	
	@Autowired
	RevenueSalesChartDao revenueSalesChartDao;
	
	@Autowired
	CustomerAnalysisDao customerAnalysisDao;
	
	@Autowired
	private SportDao sportDao;
	
	@Autowired
	private StoreDao storeDao;

	
	@RequestMapping(value = "/getRevenueByDays", method = RequestMethod.POST)
	@ResponseBody
	public SalesChartReturnEntity getRevenueByDays(@RequestBody SalesChartGetEntity salesChartGetEntity){
		System.out.println(salesChartGetEntity);
		String fromDate = salesChartGetEntity.getFromDate();
		String toDate = salesChartGetEntity.getToDate();
		List<RevenueDto> revenuDtoList =  revenueSalesChartDao.returnDaysByRevenue(fromDate, toDate);
		HashMap<String, HashMap<String, Integer>> sportStoreMap = new HashMap<>();
		HashMap<String, HashMap<String, Integer>> storeSportMap = new HashMap<>();
		try {
			List<SportDto> sportDtoList = sportDao.fetchSportList();
			
			
			for(int i=0; i<sportDtoList.size(); i++){
				HashMap<String, Integer> storeMap =  revenueSalesChartDao.getSportWiseCount("revenue",sportDtoList.get(i).getSportId(), fromDate, toDate);
				sportStoreMap.put(sportDtoList.get(i).getSportId(), storeMap);
				
			}
			
			List<StoreDto> storeDtoList = storeDao.fetchStoreDto();
			
			for(int i=0; i<storeDtoList.size(); i++){
				HashMap<String, Integer> sportMap =  revenueSalesChartDao.getStoreWiseCount("revenue",storeDtoList.get(i).getId(), fromDate, toDate);
				storeSportMap.put(storeDtoList.get(i).getId(), sportMap);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SalesChartReturnEntity(2,2,2,revenuDtoList,null, sportStoreMap, storeSportMap);
	}
	
	@RequestMapping(value = "/getProfitByDays", method = RequestMethod.POST)
	@ResponseBody
	public SalesChartReturnEntity getProfitByDays(@RequestBody SalesChartGetEntity salesChartGetEntity){
		System.out.println(salesChartGetEntity);
		//List<RevenueDto> revenuDtoList =  revenueSalesChartDao.returnProfitByDays(salesChartGetEntity.getFromDate(), salesChartGetEntity.getToDate());
		String fromDate = salesChartGetEntity.getFromDate();
		String toDate = salesChartGetEntity.getToDate();
		List<RevenueDto> profitDtoList =  revenueSalesChartDao.returnProfitByDays(fromDate, toDate);
		HashMap<String, HashMap<String, Integer>> sportStoreMap = new HashMap<>();
		HashMap<String, HashMap<String, Integer>> storeSportMap = new HashMap<>();
		
		try {
			List<SportDto> sportDtoList = sportDao.fetchSportList();
			
			
			for(int i=0; i<sportDtoList.size(); i++){
				HashMap<String, Integer> storeMap =  revenueSalesChartDao.getSportWiseCount("profit", sportDtoList.get(i).getSportId(), fromDate, toDate);
				sportStoreMap.put(sportDtoList.get(i).getSportId(), storeMap);
				
			}
			
			List<StoreDto> storeDtoList = storeDao.fetchStoreDto();
			
			for(int i=0; i<storeDtoList.size(); i++){
				HashMap<String, Integer> sportMap =  revenueSalesChartDao.getStoreWiseCount("profit", storeDtoList.get(i).getId(), fromDate, toDate);
				storeSportMap.put(storeDtoList.get(i).getId(), sportMap);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new SalesChartReturnEntity(2,2,2,null,profitDtoList,sportStoreMap,storeSportMap);
	}
	
	
	@RequestMapping(value = "/getCostumerCount", method = RequestMethod.POST)
	@ResponseBody
	public CustomerReturnEntity getCostumerCount(@RequestBody SalesChartGetEntity salesChartGetEntity){
		System.out.println(salesChartGetEntity);
		List<CustomerDto> totalCustomerDtoList =  customerAnalysisDao.getTotalCostumerCount(salesChartGetEntity.getFromDate(), salesChartGetEntity.getToDate());
		List<CustomerDto> newCustomerDtoList =  new ArrayList<>();
		List<CustomerDto> exsitingCustomerDtoList =  new ArrayList<>();
		for(int i=0; i<totalCustomerDtoList.size(); i++){
			newCustomerDtoList.add(new CustomerDto(totalCustomerDtoList.get(i).getStoreId(), (int)(totalCustomerDtoList.get(i).getCount()*0.34f),0));
			exsitingCustomerDtoList.add(new CustomerDto(totalCustomerDtoList.get(i).getStoreId(), (int)(totalCustomerDtoList.get(i).getCount()*0.66f) ,0));
		}
		return new CustomerReturnEntity(totalCustomerDtoList,newCustomerDtoList,exsitingCustomerDtoList);
	}


}
