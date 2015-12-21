package com.worksap.stm.SWARMS.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.worksap.stm.SWARMS.dao.EmployeeDao;
import com.worksap.stm.SWARMS.dao.EventDao;
import com.worksap.stm.SWARMS.dao.MarkingDao;
import com.worksap.stm.SWARMS.dao.NotificationDao;
import com.worksap.stm.SWARMS.dao.ProductDao;
import com.worksap.stm.SWARMS.dao.ProductDetailDao;
import com.worksap.stm.SWARMS.dao.SportDao;
import com.worksap.stm.SWARMS.dao.StoreDao;
import com.worksap.stm.SWARMS.dto.CustomerDto;
import com.worksap.stm.SWARMS.dto.DiscountDto;
import com.worksap.stm.SWARMS.dto.EmployeeDto;
import com.worksap.stm.SWARMS.dto.NotificationDto;
import com.worksap.stm.SWARMS.dto.PriceFeedbackDto;
import com.worksap.stm.SWARMS.dto.ProductDetailDto;
import com.worksap.stm.SWARMS.dto.ProductDto;
import com.worksap.stm.SWARMS.dto.ProfitDto;
import com.worksap.stm.SWARMS.dto.SetBestPriceReturnDto;
import com.worksap.stm.SWARMS.dto.SportDto;
import com.worksap.stm.SWARMS.dto.StoreDto;
import com.worksap.stm.SWARMS.dto.UpdateDiscountRequestDto;
import com.worksap.stm.SWARMS.entity.ComparativePrices;
import com.worksap.stm.SWARMS.entity.PriceComparisonEntity;
import com.worksap.stm.SWARMS.entity.ProductFetchEntity;
import com.worksap.stm.SWARMS.entity.ProductFilterEntity;
import com.worksap.stm.SWARMS.entity.ProductListEntity;
import com.worksap.stm.SWARMS.entity.ProductMarkingEntity;
import com.worksap.stm.SWARMS.entity.ProductProfitEntity;
import com.worksap.stm.SWARMS.entity.TopProductFilterEntity;
import com.worksap.stm.SWARMS.entity.TopReturnEntity;
import com.worksap.stm.SWARMS.entity.UpdatePriceEntity;
import com.worksap.stm.SWARMS.service.spec.EmailService;
import com.worksap.stm.SWARMS.service.spec.FeedbackService;
import com.worksap.stm.SWARMS.service.spec.MyProductService;

@Controller
public class salesManagerController {

	@Autowired
	private MyProductService myProductService;

	@Autowired
	private ProductDetailDao productDetailDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private SportDao sportDao;

	@Autowired
	private NotificationDao notificationDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private StoreDao storeDao;

	@Autowired
	private MarkingDao markingDao;

	@Autowired
	private EventDao eventDao;

	@Autowired
	private FeedbackService feedbackService;

	@PreAuthorize("MD")
	@RequestMapping("/smDashboard")
	public ModelAndView smDashboard(Principal principal) {

		// String filename = "/WEB-INF/customer.csv";

		// ServletContext context = getServletContext();
		// String fileName = System.getProperty("user.home")+"/customer.csv";
		// System.out.println(fileName);
		// csvFileReader.readCsvFile(fileName);

		// populateMarkingTable.populateMarkingTable();
		return createModelAndView(principal, "sm-dashboard");
		// return new ModelAndView("search-product2", "message",
		// "Crunchify Spring MVC with Ajax and JQuery Demo..");
	}

	@PreAuthorize("MD")
	@RequestMapping("/manageEvent")
	public ModelAndView manageEvent(Principal principal) {

		return createModelAndView(principal, "event-timeline2");
	}

	@RequestMapping("/manageProduct")
	public ModelAndView manageProduct(Principal principal) {
		System.out.println("you called salesManager");
		return createModelAndView(principal, "product-management");
		// return new ModelAndView("product-management", "message",
		// "Crunchify Spring MVC with Ajax and JQuery Demo..");

	}

	@RequestMapping("/profitMarking")
	public ModelAndView productGrouping(Principal principal) {
		System.out.println("you called salesManager");
		return createModelAndView(principal, "profit-marking2");
		// return new ModelAndView("profit-marking", "message",
		// "Crunchify Spring MVC with Ajax and JQuery Demo..");
	}

	@RequestMapping("/feedbacks")
	public ModelAndView manageFeedbacks(Principal principal) {
		return createModelAndView(principal, "sales-manager-feedback");
	}

	@RequestMapping("/priceComparisons")
	public ModelAndView managePriceComparisons(Principal principal) {
		return createModelAndView(principal, "price-comparisons");
	}
	
	@RequestMapping("/facebookAnalysis")
	public ModelAndView manageFacebookPosts(Principal principal) {
		return createModelAndView(principal, "facebook-analysis");
	}

	@SuppressWarnings("unchecked")
	private ModelAndView createModelAndView(Principal principal, String htmlPage) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) userDetails
				.getAuthorities();

		Map<String, String> model = new HashMap<String, String>();
		String role = authorities.iterator().next().getAuthority() + "";
		model.put("role", role);
		String username = principal.getName();
		EmployeeDto employeeDto = employeeDao.getByUsername(username);
		model.put("name",
				employeeDto.getFirstName() + " " + employeeDto.getLastName());
		ModelAndView model2 = new ModelAndView(htmlPage);
		model2.addObject("model", model);
		return model2;

	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	@ResponseBody
	public void addProduct(@RequestBody ProductDto productDto) {
		myProductService.insert(productDto);
	}

	@RequestMapping(value = "/getFeedbacks", method = RequestMethod.GET)
	@ResponseBody
	public List<PriceFeedbackDto> getTodaysFeedback() {
		List<PriceFeedbackDto> feedbacks = feedbackService.getTodaysFeedback();
		return feedbacks;
	}

	@RequestMapping(value = "/resolveFeedback", method = RequestMethod.GET)
	@ResponseBody
	public void resolveFeedback(@RequestParam("feedback_id") int feedbackId) {
		feedbackService.resolveFeedback(feedbackId);
	}

	@RequestMapping(value = "/getComparativePrices", method = RequestMethod.GET)
	@ResponseBody
	public ComparativePrices getComparativePrices(
			@RequestParam("product_id") String pid) {
		ComparativePrices price = feedbackService.getComparativePrices(pid);
		return price;
	}

	@RequestMapping(value = "/getUpdatedPriceEntity", method = RequestMethod.GET)
	@ResponseBody
	public UpdatePriceEntity getUpdatePriceEntity(
			@RequestParam("product_id") String pid) {
		UpdatePriceEntity updatePriceEnity = feedbackService
				.getUpdatePriceEntity(pid);
		return updatePriceEnity;
	}

	@RequestMapping(value = "/getAllPriceComparisons", method = RequestMethod.GET)
	@ResponseBody
	public List<PriceComparisonEntity> getAllPriceComparisons() {
		List<PriceComparisonEntity> priceComparisons = feedbackService
				.getAllPriceComparisons();
		return priceComparisons;
	}

	@RequestMapping(value = "/refreshPriceComparisons", method = RequestMethod.GET)
	@ResponseBody
	public int refreshPriceComparisons() {
		return feedbackService.refreshPriceComparisons();
	}

	@RequestMapping(value = "/setBestPrices", method = RequestMethod.GET)
	@ResponseBody
	public SetBestPriceReturnDto setBestPrices() {
		return feedbackService.setBestPrices();
	}

	@RequestMapping(value = "/getPreviousDiscounts", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductMarkingEntity> returnProductData(
			@RequestParam("product_id") String pid) {
		return feedbackService.getAllPreviousDiscounts(pid);
	}

	@RequestMapping(value = "/updateDiscountPrice", method = RequestMethod.POST)
	@ResponseBody
	public void updateDiscountPercent(
			@RequestBody UpdateDiscountRequestDto requestEntity) {
		feedbackService.updateDiscountPercent(requestEntity.getProductId(),
				requestEntity.getNewDiscount(),
				requestEntity.getProcurmentPrice(), requestEntity.getMrp());
	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductDto> getAllProduct(
			@RequestParam("sport_id") String sport_id,
			@RequestParam("brand") String brand,
			@RequestParam("textSearchInput") String ti) {
		System.out.println("At getAllProduct " + sport_id + " " + brand + " "
				+ ti);
		return productDao.getProductsListByFilter(sport_id, brand, ti);
	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/returnProductData", method = RequestMethod.POST)
	@ResponseBody
	public Object returnProductData(
			@RequestBody ProductFilterEntity productFilterEntity) {
		System.out.println("productFilterEntity = " + productFilterEntity);
		try {
			// System.out.println("profitMarkingEntity = " +
			// productDetailDao.getProfitMarkingList());
			return new ProductListEntity(2, 2, 2,
					productDetailDao.getProfitMarkingList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/upateProfitMargin", method = RequestMethod.POST)
	@ResponseBody
	public void upateProfitMargin(
			@RequestBody ProductProfitEntity productProfitEntity) {
		// System.out.println("productFilterEntity = " +
		// productFilterEntity.getGroupType());
		myProductService.upateProfitMargin(productProfitEntity);

	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/upateProfitMarginById", method = RequestMethod.GET)
	@ResponseBody
	public void upateProfitMarginById(@RequestParam("id") String id,
			@RequestParam("profitId") String profitId) {
		// System.out.println("productFilterEntity = " +
		// productFilterEntity.getGroupType());
		System.out.println(id + " " + profitId);
		markingDao.upateProfitMarkingGroup(id, profitId);

	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/updateDiscountByID", method = RequestMethod.GET)
	@ResponseBody
	public void updateDiscountByID(@RequestParam("id") String id,
			@RequestParam("discountId") String discountId) {
		// System.out.println("productFilterEntity = " +
		// productFilterEntity.getGroupType());
		System.out.println(id + " " + discountId);
		markingDao.upateDiscountMarkingGroup(id, discountId);
	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/editProduct", method = RequestMethod.POST)
	@ResponseBody
	public void editProduct(@RequestBody ProductDto productDto) {
		myProductService.update(productDto);

	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/addProductDetail", method = RequestMethod.POST)
	@ResponseBody
	public void addProductDetail(@RequestBody ProductDetailDto productDetailDto) {
		myProductService.insertProductDetail(productDetailDto);
		System.out.println("Qty = " + productDetailDto.getQty());
		if (productDetailDto.getQty() > 0) {
			List<CustomerDto> list = emailService
					.getListOfCustomerDtoForAvailableProduct(productDetailDto);
			System.out.println("CustomerDtoList Size = " + list.size());
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			new Thread(new Runnable() {
				public void run() {
					// Do whatever
					emailService.mailing(list, productDetailDto);
				}
			}).start();

		}
	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getPrice", method = RequestMethod.GET)
	@ResponseBody
	public int getPrice(@RequestParam("pid") String pid,
			@RequestParam("color") String color,
			@RequestParam("size") String size,
			@RequestParam("storeId") String storeId) {
		System.out.println(pid + " " + color + " " + size + " " + storeId);
		// return customerService.getCustomerById(id);
		return productDetailDao.fetchPrice(pid, color, size, storeId);
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
	public ProductFetchEntity getProductDetailById(@RequestParam("id") String id) {
		try {
			return productDetailDao.fetchProductDetailByPid(Integer
					.parseInt(id));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/updateProductQty", method = RequestMethod.GET)
	@ResponseBody
	public void updateProductQty(@RequestParam("id") String id,
			@RequestParam("wq") String wq, @RequestParam("ipq") String ipq,
			@RequestParam("nid") String nid,
			@RequestParam("status") String status) {
		System.out.println("id = " + id + "wq = " + wq + "ipq =" + ipq
				+ "nid =" + nid + "status = " + status);
		int op = Integer.parseInt(status);
		if (op == 3)
			productDetailDao.updateProductQty(Integer.parseInt(id),
					Integer.parseInt(wq), Integer.parseInt(ipq));
		if (op == 3 || op == 2)
			notificationDao.deleteNotification(Integer.parseInt(nid));
		if (op == 1)
			notificationDao.updateSeenStatus(Integer.parseInt(nid));
	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/getBrandList", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getBrandList(@RequestParam("sport_id") String sportId) {
		System.out.println("id = " + sportId);
		System.out.println("done");

		List<String> res = productDao.getBrandList(sportId);
		System.out.println(res);

		return res;

		// int op = Integer.parseInt(status);

	}

	@PreAuthorize("hasAuthority('MD')")
	@RequestMapping(value = "/fetchStoreList", method = RequestMethod.GET)
	@ResponseBody
	public List<StoreDto> fetchStoreList() {

		List<StoreDto> res = storeDao.fetchStoreDto();
		System.out.println(res);

		return res;
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
	@RequestMapping(value = "/fetchDiscountList", method = RequestMethod.GET)
	@ResponseBody
	public List<DiscountDto> fetchDiscountList() {

		List<DiscountDto> res;
		try {
			res = productDetailDao.DiscountDtoList();
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(res);
		return null;

	}

	@RequestMapping(value = "/fetchDataOfTopProducts", method = RequestMethod.POST)
	@ResponseBody
	public TopReturnEntity fetchDataOfTopProducts(
			@RequestBody TopProductFilterEntity topProductFilterEntity) {
		System.out.println(topProductFilterEntity);
		return new TopReturnEntity(
				2,
				2,
				2,
				eventDao.getTopProductsData(topProductFilterEntity.getEventId()));

	}
}
