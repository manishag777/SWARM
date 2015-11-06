package com.worksap.stm.SWARMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.service.spec.MyProductService;


@Controller
public class salesManagerController {
	
	@Autowired
	private MyProductService myProductService;
	
	
	@RequestMapping("/manageProduct")
    public ModelAndView helloAjaxTest() {
		System.out.println("you called salesManager");
        return new ModelAndView("product-management", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");

    }
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST )
	@ResponseBody
	public void addProduct(@RequestBody ProductDto productDto) {	
		myProductService.insert(productDto);
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/editProduct", method = RequestMethod.POST )
	@ResponseBody
	public void editProduct(@RequestBody ProductDto productDto) {	
		myProductService.update(productDto);
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/addProductDetail", method = RequestMethod.POST )
	@ResponseBody
	public void addUserAccount(@RequestBody ProductDetailDto productDetailDto) {	
		myProductService.insertProductDetail(productDetailDto);
	}
	
	
	
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductDto> returnEmployeData() {
		return myProductService.getAllProduct();
	}
	
	
	/*@RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    String getTime() {
        String result = "<br>Response from Ajax</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller..");
        return result;
    }
*/
}
