package com.worksap.stm.SWARMS.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dao.TemplateMailDao;
import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.GiftCardDto;
import com.worksap.stm.SWARMS.service.spec.CustomerRelationService;
import com.worksap.stm.SWARMS.dto.TemplatMailDto;


@Controller
public class CSOController {
	
	@Autowired
	CustomerRelationService customerRelationService;
	
	@Autowired
	TemplateMailDao templateMailDao;
	
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
	
	@RequestMapping("/templateMailingManagement")
    public ModelAndView templateMailingManagement() {
        return new ModelAndView("template-mailing", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
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
	
	
}
