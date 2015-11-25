package com.worksap.stm.SWARMS.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dao.EmployeeDao;
import com.worksap.stm.SWARMS.dao.TextSearchProductDao;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
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
	
	@Autowired
	private TextSearchProductDao textSearchProductDao;
	
	@Autowired
	private EmployeeDao employeeDao;


	
	
	
	@RequestMapping("/searchProduct")
    public ModelAndView manageProduct(Principal principal) {
        //return new ModelAndView("search-product");
		return createModelAndView(principal, "search-product2");
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
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/returnFilteredProducts", method = RequestMethod.POST)
	@ResponseBody
	public ProductSearchFetchListEntity returnFilteredProducts(@RequestBody ProductSearchFilterEntity productSearchFilterEntity) {
		System.out.println("At productSearchFilterEntity");
		System.out.println(productSearchFilterEntity);
		//System.out.println("productFilterEntity = " + productFilterEntity.getGroupType());
		//return new ProductSearchFetchListEntity(2,2,2,myProductService.returnFilteredProducts(productSearchFilterEntity));
		//return new ProductSearchFetchListEntity(2,2,2,null);
		return new ProductSearchFetchListEntity(2,2,2,textSearchProductDao.searchProductByFilter(productSearchFilterEntity));	
	}
	
//	@PreAuthorize("hasAuthority('MD')")
//	@RequestMapping(value = "/searchProductByFilter", method = RequestMethod.GET )
//	@ResponseBody
//	public void searchProductByFilter(@RequestParam("pid") String pid, @RequestParam("name") String name, @RequestParam("brand") String brand ) {	
//
//		System.out.println(pid + " "+ name+" "+brand);
//		textSearchProductDao.searchProductByFilter(pid,name,brand);
//		//return customerService.getCustomerById(id);
//		//return productDetailDao.fetchPrice(pid, color, size, storeId);
//	}
	
		
	
}
