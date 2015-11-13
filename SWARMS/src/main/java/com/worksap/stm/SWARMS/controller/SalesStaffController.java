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

import com.worksap.stm.SWARMS.dto.WishListDto;
import com.worksap.stm.SWARMS.entity.ProductProfitEntity;
import com.worksap.stm.SWARMS.entity.ProductSearchFetchListEntity;
import com.worksap.stm.SWARMS.entity.ProductSearchFilterEntity;
import com.worksap.stm.SWARMS.service.spec.CustomerService;
import com.worksap.stm.SWARMS.service.spec.MyProductService;
import com.worksap.stm.SWARMS.service.spec.WishListService;

@Controller
public class SalesStaffController {
	
	@Autowired
	private MyProductService myProductService;
	
	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private CustomerService customerService;
	
	
	
	@RequestMapping("/searchProduct")
    public ModelAndView manageProduct() {
        return new ModelAndView("search-product");
    }
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/returnFilteredProducts", method = RequestMethod.POST)
	@ResponseBody
	public ProductSearchFetchListEntity returnFilteredProducts(@RequestBody ProductSearchFilterEntity productSearchFilterEntity) {
		//System.out.println("productFilterEntity = " + productFilterEntity.getGroupType());
		return new ProductSearchFetchListEntity(2,2,2,myProductService.returnFilteredProducts(productSearchFilterEntity));
			
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/addWishList", method = RequestMethod.POST)
	@ResponseBody
	public void addWishList(@RequestBody WishListDto wishListDto) {
		System.out.println(wishListDto);
		wishListService.insert(wishListDto);
		
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/updateEmailId", method = RequestMethod.GET)
	@ResponseBody
	public void updateEmailId(@RequestParam("id") int id, @RequestParam("emailId") String emailId) {
		System.out.println(id +" " + emailId);
		customerService.updateEmailId(id,emailId);
	}
}
