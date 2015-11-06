package com.worksap.stm.SWARMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.GiftCardDetailDto;
import com.worksap.stm.SWARMS.dto.GiftCardDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.service.spec.CustomerRelationService;
import com.worksap.stm.SWARMS.service.spec.CustomerService;
import com.worksap.stm.SWARMS.service.spec.MyProductService;

@Controller
public class CashierController {
	
	@Autowired
	 private CustomerService customerService;
	
	@Autowired
	 private MyProductService productService;
	
	@Autowired
	CustomerRelationService customerRelationService;

	
	@RequestMapping("/manageCustomer")
    public ModelAndView helloAjaxTest() {
		System.out.println("you called Cashier");
        return new ModelAndView("billing", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");

    }
	
	
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST )
	@ResponseBody
	public int addCustomer(@RequestBody CustomerDto customerDto) {	
		return customerService.insert(customerDto);
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/editCustomer", method = RequestMethod.POST )
	@ResponseBody
	public void editCustomer(@RequestBody CustomerDto customerDto) {	
		customerService.update(customerDto);
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getProductInfoByPid", method = RequestMethod.GET )
	@ResponseBody
	public ProductDto GetProductInfoByPid(@RequestParam("pid") String pid) {	
		
		System.out.println(pid);
		return productService.getProductById(pid);
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getProductDetails", method = RequestMethod.GET )
	@ResponseBody
	public ProductDetailDto GetProductDetails(@RequestParam("pid") String pid, @RequestParam("size") String size, @RequestParam("color") String color) {	
		
		System.out.println(pid);
		return productService.getProductDetail(pid,"ranchi",size,color);
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getCustomerInfoById", method = RequestMethod.GET )
	@ResponseBody
	public CustomerDto GetCustomerInfoById(@RequestParam("id") String id) {	
		System.out.println(id);
		return customerService.getCustomerById(id);
	}
	
	
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/giftCardStatusByCustomerId", method = RequestMethod.GET )
	@ResponseBody
	public GiftCardDetailDto getGiftCardSpecification(@RequestParam("id") int id) {	
		return customerRelationService.getGiftCardSpecification(id);
	}
}
