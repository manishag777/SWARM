package com.worksap.stm.SWARMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.GiftCardDto;
import com.worksap.stm.SWARMS.service.spec.CustomerRelationService;


@Controller
public class CSOController {
	
	@Autowired
	CustomerRelationService customerRelationService;
	
	@RequestMapping("/manageGiftCard")
    public ModelAndView helloAjaxTest() {
		System.out.println("you called CSO");
        return new ModelAndView("gift-card-specification", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
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
	
	
	
	
}
