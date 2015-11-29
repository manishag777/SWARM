package com.worksap.stm.SWARMS.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dao.CustomerDao;
import com.worksap.stm.SWARMS.dao.EmployeeDao;
import com.worksap.stm.SWARMS.dao.EventDao;
import com.worksap.stm.SWARMS.dao.TemplateMailDao;
import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.EventDto;
import com.worksap.stm.SWARMS.dto.GiftCardDto;
import com.worksap.stm.SWARMS.service.spec.CustomerRelationService;
import com.worksap.stm.SWARMS.utils.KmeanClustering;
import com.worksap.stm.SWARMS.dto.TemplatMailDto;
import com.worksap.stm.SWARMS.entity.CustomerClusterEntity;
import com.worksap.stm.SWARMS.entity.StoreFetchEntity;


@Controller
public class CSOController {
	
	@Autowired
	CustomerRelationService customerRelationService;
	
	@Autowired
	TemplateMailDao templateMailDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	KmeanClustering kmeanClustering;
	
	@Autowired
	EventDao eventDao;
	
	@RequestMapping("/manageGiftCard")
    public ModelAndView manageGiftCard(Principal principal) {
		System.out.println("you called CSO");
//        return new ModelAndView("gift-card-specification", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
//        UserDetails userDetails =
//				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Collection<SimpleGrantedAuthority> authorities  = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();
//		
//
//		Map<String,String> model = new HashMap<String,String>();
//		String role = authorities.iterator().next().getAuthority()+"";
//		model.put("role", role);
//		String username  = principal.getName();
//		EmployeeDto  employeeDto = employeeDao.getByUsername(username);
//		model.put("name", employeeDto.getFirstName() + " "+employeeDto.getLastName());
//
//		ModelAndView model2 = new ModelAndView("gift-card-specification");
//		model2.addObject("model", model);

		return createModelAndView(principal,"gift-card-specification");
	
	}
	
	@RequestMapping("/suggestionForOpeningNewStore")
    public ModelAndView suggestionForOpeningNewStore(Principal principal) {
		System.out.println("you called CSO");
       // return new ModelAndView("store-opening-suggestion", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
		return createModelAndView(principal,"store-opening-suggestion2");

    }
	
	@RequestMapping("/templateMailingManagement")
    public ModelAndView templateMailingManagement(Principal principal) {
        //return new ModelAndView("template-mailing", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
		return createModelAndView(principal,"template-mailing");

    }
	
	private ModelAndView createModelAndView(Principal principal, String htmlPage){
		
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<SimpleGrantedAuthority> authorities  = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();
		

		Map<String,String> model = new HashMap<String,String>();
		String role = authorities.iterator().next().getAuthority()+"";
		model.put("role", role);
		String username  = principal.getName();
		EmployeeDto  employeeDto = employeeDao.getByUsername(username);
		model.put("name", employeeDto.getFirstName() + " "+employeeDto.getLastName());
		ModelAndView model2 = new ModelAndView(htmlPage);
		model2.addObject("model", model);

		return model2;
		
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/updateGiftCard", method = RequestMethod.POST )
	@ResponseBody
	public void editCustomer(@RequestBody GiftCardDto giftCardDto) {	
		customerRelationService.updateGiftCard(giftCardDto);
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getGiftCardSpecification", method = RequestMethod.GET )
	@ResponseBody
	public GiftCardDto getGiftCardSpecification() {	
		return customerRelationService.fetchGiftCardDetail();
	}
		
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/addTemplateMail", method = RequestMethod.POST )
	@ResponseBody
    public void addTemplateMail(@RequestBody TemplatMailDto templateMailDto) {
		
		System.out.println(templateMailDto);
		
	try {
			String tag = templateMailDto.getTagText();
			if(tag.charAt(0)=='$'){
				int index = tag.indexOf('#');
				//int length = index-1;
				String oldTag = tag.substring(1, index);
				String newTag = tag.substring(index+1);
				System.out.println(oldTag+" "+newTag);
				templateMailDao.removeTemplate(oldTag);
				templateMailDto.setTagText(newTag);

			}
			templateMailDao.insertTemplate(templateMailDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/fetchAllTemplates", method = RequestMethod.GET )
	@ResponseBody
	public List<TemplatMailDto> fetchAllTemplates() {	
		return templateMailDao.fetchAllTemplates();
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/fetchClusterCustomerDto", method = RequestMethod.GET )
	@ResponseBody
	public CustomerClusterEntity fetchClusterCustomerDto() {	
		
		try {
			List<CustomerDto> customerDtoList = customerDao.getAllCustomer();
			/*for(int i=0; i<customerDtoList.size(); i++){
				System.out.println(customerDtoList.get(i));
			}*/
			return kmeanClustering.findKMeanCluster(customerDtoList, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getClusteringResult", method = RequestMethod.POST )
	@ResponseBody
	public CustomerClusterEntity getClusteringResult(@RequestBody List<StoreFetchEntity> storeFetchEntities ){
		
		for(int i=0; i<storeFetchEntities.size(); i++)
			System.out.println(storeFetchEntities.get(i));
		
		return kmeanClustering.findClusterEntities(storeFetchEntities);
	}
	
	@RequestMapping(value = "/addEvent", method = RequestMethod.POST )
	@ResponseBody
	public int addEvent(@RequestBody EventDto eventDto ){
		
		//List<StoreFetchEntity> storeFetchEntities = customerClusterEntity
		System.out.println(eventDto);
		
//		for(int i=0; i<storeFetchEntities.size(); i++)
//			System.out.println(eventDao.get(i));
		
		try {
			return eventDao.insert(eventDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	
	
	
	}
}
