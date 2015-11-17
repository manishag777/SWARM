package com.worksap.stm.SWARMS.controller;

import java.io.IOException;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dao.NotificationDao;
import com.worksap.stm.SWARMS.dao.ProductDetailDao;
import com.worksap.stm.SWARMS.dao.SportDao;
import com.worksap.stm.SWARMS.dao.TextSearchProductDao;
import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.NotificationDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.dto.ProfitDto;
import com.worksap.stm.SWARMS.dto.SportDto;
import com.worksap.stm.SWARMS.entity.EmployeeEntity;
import com.worksap.stm.SWARMS.entity.EmployeeListEntity;
import com.worksap.stm.SWARMS.entity.ProductFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductFilterEntity;
import com.worksap.stm.SWARMS.entity.ProductListEntity;
import com.worksap.stm.SWARMS.entity.ProductProfitEntity;
import com.worksap.stm.SWARMS.service.spec.EmailService;
import com.worksap.stm.SWARMS.service.spec.MyProductService;


@Controller
public class salesManagerController {
	
	@Autowired
	private MyProductService myProductService;
	
	@Autowired
	private ProductDetailDao  productDetailDao;
	
	@Autowired
	private EmailService  emailService;
	
	@Autowired
	private SportDao  sportDao;
	
	@Autowired
	private NotificationDao notificationDao;
	
	@RequestMapping("/manageProduct")
    public ModelAndView manageProduct() {
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
	@RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductDto> returnEmployeData(@RequestParam("sport_id") String sport_id) {
		return myProductService.getAllProduct(sport_id);
	}

	
	@RequestMapping("/profitMarking")
    public ModelAndView productGrouping() {
		System.out.println("you called salesManager");
        return new ModelAndView("profit-marking", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
    }
	
	
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/returnProductData", method = RequestMethod.POST)
	@ResponseBody
	public Object returnProductData(@RequestBody ProductFilterEntity productFilterEntity) {
		System.out.println("productFilterEntity = " + productFilterEntity.getGroupType());
		return new ProductListEntity(2,2,2,myProductService.getProductListEntity(productFilterEntity));
			
	}
	

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/upateProfitMargin", method = RequestMethod.POST)
	@ResponseBody
	public void upateProfitMargin(@RequestBody ProductProfitEntity productProfitEntity) {
		//System.out.println("productFilterEntity = " + productFilterEntity.getGroupType());
		myProductService.upateProfitMargin(productProfitEntity);
			
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
	public void addProductDetail(@RequestBody ProductDetailDto productDetailDto) {	
		myProductService.insertProductDetail(productDetailDto);
		System.out.println("Qty = " +productDetailDto.getQty());
		if(productDetailDto.getQty()>0){
			List<CustomerDto> list = emailService.getListOfCustomerDtoForAvailableProduct(productDetailDto);
			System.out.println("CustomerDtoList Size = " +list.size());
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i));
			}
			new Thread(new Runnable() {
			    public void run() {
			        //Do whatever
			    	emailService.mailing(list, productDetailDto);
			    }
			}).start();
			
		}
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getPrice", method = RequestMethod.GET )
	@ResponseBody
	public int getPrice(@RequestParam("pid") String pid, @RequestParam("color") String color, @RequestParam("size") String size,@RequestParam("storeId") String storeId ) {	
		System.out.println(pid + " "+ color+" "+size+" "+storeId);
		//return customerService.getCustomerById(id);
		return productDetailDao.fetchPrice(pid, color, size, storeId);
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/fetchProfitList", method = RequestMethod.GET)
	@ResponseBody
	public List<ProfitDto> fetchProfitList() {
		try {
			return productDetailDao.ProductprofitDtoList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/fetchSportList", method = RequestMethod.GET)
	@ResponseBody
	public List<SportDto> fetchSportList() {
		try {
			return sportDao.fetchSportList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getNotifications", method = RequestMethod.GET)
	@ResponseBody
	public List<NotificationDto> getNotification() {
		try {
			return notificationDao.getNotification();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getProductDetailById", method = RequestMethod.GET)
	@ResponseBody
	public ProductFetchEntity getProductDetailById( @RequestParam("id") String id) {
		try {
			return productDetailDao.fetchProductDetailByPid(Integer.parseInt(id));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/updateProductQty", method = RequestMethod.GET)
	@ResponseBody
	public void updateProductQty( @RequestParam("id") String id, @RequestParam("wq") String wq, @RequestParam("ipq") String ipq,  @RequestParam("nid") String nid, @RequestParam("status") String status) {
		System.out.println("id = "+id + "wq = "+wq + "ipq ="+ipq + "nid ="+nid + "status = "+ status);
		int op = Integer.parseInt(status);
		if(op==3)
			productDetailDao.updateProductQty(Integer.parseInt(id), Integer.parseInt(wq), Integer.parseInt(ipq));
		if(op==3 || op ==2)
			notificationDao.deleteNotification(Integer.parseInt(nid));
		if(op==1)
			notificationDao.updateSeenStatus(Integer.parseInt(nid));

		
	}	
}
