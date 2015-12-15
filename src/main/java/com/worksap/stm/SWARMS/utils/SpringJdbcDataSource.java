package com.worksap.stm.SWARMS.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;



import javax.sql.DataSource;



import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;



import com.mysql.jdbc.Statement;
import com.worksap.stm.SWARMS.dto.OrderDetailDto;
import com.worksap.stm.SWARMS.dto.OrderDto;

public class SpringJdbcDataSource {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/s150";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "adm1n";
	public static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static Random rnd = new Random();
	private static final String INSERT_PRODUCT = "INSERT INTO product_new "
			+ " (pid, sport_id, name, brand, info, aval_size, aval_color, iurl)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_PRODUCT_DETAIL = "INSERT INTO product_detail_new "
			+ " (pid, store_id, size, color, price, margin_old, discount, warning_qty, qty, profit_id)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String INSERT_EVENT = "INSERT INTO events "
			+ " (sport_id, start_date, end_date, event_details, location, interested_store_id)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	private static DateFormat targetDF = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat inputDF = new SimpleDateFormat("dd-MMM-yyyy");

	public static void mainNotValid(String[] args) throws IOException {
		DataSource source = getDataSource();
		JdbcTemplate template = new JdbcTemplate(source);

		/*
		 //Product generation // read file 
		 BufferedReader reader = new BufferedReader(new FileReader( "/Users/mukesh/Desktop/input.txt"));
		 String line; 
		 while ((line = reader.readLine()) != null) { 
			 String split[] = line.split("#"); 
			 String pid = randomString(10); 
			 try {
				 template.update(INSERT_PRODUCT, (ps) -> { ps.setString(1, pid);
				 	ps.setString(2, "tennis"); ps.setString(3, split[0].trim()); ps.setString(4,
		 split[1].trim()); ps.setString(5, split[0].trim()); ps.setString(6,
		 "default"); ps.setString(7, "yellow_red"); ps.setString(8, split[3].trim());
		 
		 }); } catch (Exception e) {
		  
		 } 
		 String sizes[] = { "default"}; 
		 String colors[] = {"red", "yellow"}; 
		 for (String size : sizes) { 
			 for (String color : colors) { 
				 try { 
					 template.update(INSERT_PRODUCT_DETAIL, (ps)
							 -> { ps.setString(1, pid); 
							 	ps.setString(2, getStoreId()); 
							 	ps.setString(3, size);
							 	ps.setString(4, color); 
							 	ps.setInt(5,Integer.parseInt(split[2].trim())); 
							 	ps.setInt(6, 25); 
							 	ps.setInt(7,getDiscount()); 
							 	ps.setInt(8, getWarningQty()); 
							 	ps.setInt(9,getQty()); 
							 	ps.setInt(10, getProfitId());
		  
		 }); } catch (DataAccessException e) {
		  
		 System.out.println("Adding product Details :" + e); throw new
		 IOException(e); } } }
		 
		 } 
		 */

		/*
		 * // event generation BufferedReader reader = new BufferedReader(new
		 * FileReader("/Users/mukesh/Desktop/input.txt")); String line; while
		 * ((line = reader.readLine()) != null) { String split[] =
		 * line.split("#"); Date date = new Date(); try { date =
		 * inputDF.parse(split[0].trim());
		 * 
		 * } catch (ParseException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } String targetDateString =
		 * targetDF.format(date); try { template.update(INSERT_EVENT, (ps) -> {
		 * ps.setString(1, split[3].trim()); ps.setString(2, targetDateString);
		 * ps.setString(3, targetDateString); ps.setString(4, split[1].trim());
		 * ps.setString(5, split[2].trim()); ps.setString(6, "delhi");
		 * 
		 * }); } catch (Exception e) {
		 * 
		 * }
		 * 
		 * } reader.close();
		 */
		/*
		String sql = "select pid from product where sport_id=\'cricket\' and name like \'%Bat%\'";
		List<String> pids1 = template.queryForList(sql, String.class);
		
		String sql2 = "select pid from product where sport_id=\'cricket\' and name like \'%Ball%\'";
		List<String> pids2 = template.queryForList(sql2, String.class);
		
		String sql3 = "insert into purchased_together_items(pid1, pid2) values(?,?)";
		int pid2Size = pids2.size();
		for(String pid : pids1) 
		{
			int rand1 = (int)(Math.random()*pid2Size);
			template.update(sql3, new Object[]{pid, pids2.get(rand1)});
			int rand2 = (int)(Math.random()*pid2Size);
			template.update(sql3, new Object[]{pid, pids2.get(rand2)});
		}*/
		
		/*
		List<Products> products = new ArrayList<>();
		String sql = "SELECT a.id, a.price, b.preDate, b.preProfitPercent, b.preDiscountPercent, "
				+ " b.currDate, b.currProfitPercent, b.currDiscountPercent "
				+ "FROM product_detail a, marking b where a.id = b.pid";
		template.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Products product = new Products(rs.getInt("id"), rs
						.getInt("price"), rs.getDate("preDate"), rs
						.getInt("preProfitPercent"), rs
						.getInt("preDiscountPercent"), rs.getDate("currDate"),
						rs.getInt("currProfitPercent"), rs
								.getInt("currDiscountPercent"));
				products.add(product);
			}
		});
		int numProucts = products.size();
		int actualNumProducts = numProucts;
		Calendar calendar = Calendar.getInstance();
		int date = 1;

		for (int i = 0; i < 333; i++) {
			calendar.set(2015, 0, date + i, 10, 15, 0);
			int numOrders = ((int) (Math.random() * 10)) + 20;
			for (int j = 0; j < numOrders; j++) {
				calendar.add(Calendar.MINUTE, 10);
				int custId = getCustomerId();
				int itemsCount = ((int) (Math.random() * 10)) + 1;
				OrderDto order = new OrderDto();
				order.setCustId(custId);
				order.setGcDiscount(0);
				order.setStaffId("");
				order.setStoreId("delhi");

				List<OrderDetailDto> orderDetailList = new LinkedList<OrderDetailDto>();
				double amount = 0;
				for (int k = 1; k <= itemsCount; k++) {
					int productId = getProductId(actualNumProducts);
					OrderDetailDto orderDetail = new OrderDetailDto();
					Products product = products.get(productId);
					orderDetail.setPid(product.id);
					orderDetail.setCp(product.price);
					int discount = getDiscount(product, calendar.getTime());
					orderDetail.setDiscount(discount);
					int margin = getProfit(product, calendar.getTime());
					orderDetail.setMargin(margin);
					int qty = ((int) (Math.random() * 10)) + 1;
					orderDetail.setQty(qty);
					double actualPrice = (product.price * 1.0) + product.price
							* (margin * 1.0 / 100.0);
					actualPrice = actualPrice
							- (actualPrice * (discount * 1.0 / 100.0));
					amount += (qty * actualPrice);
					orderDetailList.add(orderDetail);
				}
				order.setSubTotal((int) amount);
				order.setStaffId("neeraj");
				int orderId = insertOrder(order, template, calendar);
				insertOrderDetail(orderDetailList, orderId, template);
			}
		}
		
		*/
		/*
		List<Events> events = new ArrayList<>();
		String eventSql = "SELECT sport_id, start_date, end_date FROM events";
		template.query(eventSql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Events event = new Events();
				event.sportId = rs.getString("sport_id");
				event.startDate = rs.getDate("start_date");
				event.endDate = rs.getDate("end_date");
				events.add(event);
			}
		});
		*/
		List<PriceProduct> products = new ArrayList<>();
		String sql = "SELECT pid, mrp, discount_percent from profit_marking where end_date IS NULL";
		template.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				PriceProduct product = new PriceProduct(rs.getString("pid"), rs.getInt("mrp"), rs.getInt("discount_percent"));
				products.add(product);
			}
		});
		double variations[] = {0.1, -0.1, 0.15, -0.15, 0.2, -0.2};
		String sql1 = "INSERT INTO price_comparisons values(?,?,?,?)";
		for(PriceProduct product : products) {
			int actPrice = product.price - (int)(product.price*1.0 * (product.discount*1.0 /100.0));
			int amazonPrice = (int) (actPrice - actPrice*(variations[(int) (Math.random()*6)]));
			int ebayPrice = (int) (actPrice - actPrice*(variations[(int) (Math.random()*6)]));
			template.update(sql1, new Object[] { product.pid, actPrice, amazonPrice, ebayPrice });
		}
		/*
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < events.size(); i++) {
			Events event = events.get(i);
			Date date = event.endDate;
			calendar.setTime(date);
			
			int numDaysBefore = getBeforeDayCount();
			for(int day = numDaysBefore; day>0; day--)
			{
				calendar.add(Calendar.DAY_OF_MONTH, -1 * numDaysBefore);
				calendar.set(Calendar.HOUR_OF_DAY, 10);
				calendar.set(Calendar.MINUTE, 17);
				
				int numOrders = ((int) (Math.random() * 20)) + 15;
				List<Products> relatedProducts = productMap.get(event.sportId);
				int actualNumProducts = relatedProducts.size();
				for (int j = 0; j < numOrders; j++) {
					calendar.add(Calendar.MINUTE, 10);
					int custId = getCustomerId();
					int itemsCount = ((int) (Math.random() * 10)) + 1;
					OrderDto order = new OrderDto();
					order.setCustId(custId);
					order.setGcDiscount(0);
					order.setStaffId("");
					order.setStoreId("delhi");
	
					List<OrderDetailDto> orderDetailList = new LinkedList<OrderDetailDto>();
					double amount = 0;
					for (int k = 1; k <= itemsCount; k++) {
						int productId = getProductId(actualNumProducts);
						OrderDetailDto orderDetail = new OrderDetailDto();
						Products product = relatedProducts.get(productId);
						orderDetail.setPid(product.id);
						orderDetail.setCp(product.price);
						int discount = getDiscount(product, calendar.getTime());
						orderDetail.setDiscount(discount);
						int margin = getProfit(product, calendar.getTime());
						orderDetail.setMargin(margin);
						int qty = ((int) (Math.random() * 10)) + 1;
						orderDetail.setQty(qty);
						double actualPrice = (product.price * 1.0) + product.price
								* (margin * 1.0 / 100.0);
						actualPrice = actualPrice
								- (actualPrice * (discount * 1.0 / 100.0));
						amount += (qty * actualPrice);
						orderDetailList.add(orderDetail);
					}
					order.setSubTotal((int) amount);
					order.setStaffId("neeraj");
					int orderId = insertOrder(order, template, calendar);
					insertOrderDetail(orderDetailList, orderId, template);
				}
			}
		}
		*/
		/*
		// frequent customer generation
		List<Products> products = new ArrayList<>();
		String sql = "SELECT a.id, a.price, b.preDate, b.preProfitPercent, b.preDiscountPercent, "
				+ " b.currDate, b.currProfitPercent, b.currDiscountPercent "
				+ "FROM product_detail a, marking b where a.id = b.pid";
		template.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Products product = new Products(rs.getInt("id"), rs
						.getInt("price"), rs.getDate("preDate"), rs
						.getInt("preProfitPercent"), rs
						.getInt("preDiscountPercent"), rs.getDate("currDate"),
						rs.getInt("currProfitPercent"), rs
								.getInt("currDiscountPercent"));
				products.add(product);
			}
		});
		int numProucts = products.size();
		int actualNumProducts = numProucts;
		Calendar calendar = Calendar.getInstance();
		
		boolean isCustomerDone[] = new boolean[1805]; 
		for(int group =0; group<9; group++)
		{
			int numCustomers = getNumCustomers(group);
			for(int i=0; i< numCustomers; i++)
			{
				int custId = getCustomerId();
				while(isCustomerDone[custId]) {
					custId = getCustomerId();
				}
				isCustomerDone[custId] = true;
				int numTransactions = getNumTransactions(group);
				for(int j=0; j< numTransactions; j++)
				{
					calendar.set(2015, (int)(Math.random()*12), (int)(Math.random()*29), (int)(Math.random()*9) + 10, (int)(Math.random()*60), 0);
					calendar.add(Calendar.MINUTE, 10);
					int itemsCount = ((int) (Math.random() * 10)) + 1;
					OrderDto order = new OrderDto();
					order.setCustId(custId);
					order.setGcDiscount(0);
					order.setStaffId("");
					order.setStoreId("delhi");

					List<OrderDetailDto> orderDetailList = new LinkedList<OrderDetailDto>();
					double amount = 0;
					for (int k = 1; k <= itemsCount; k++) {
						int productId = getProductId(actualNumProducts);
						OrderDetailDto orderDetail = new OrderDetailDto();
						Products product = products.get(productId);
						orderDetail.setPid(product.id);
						orderDetail.setCp(product.price);
						int discount = getDiscount(product, calendar.getTime());
						orderDetail.setDiscount(discount);
						int margin = getProfit(product, calendar.getTime());
						orderDetail.setMargin(margin);
						int qty = ((int) (Math.random() * 10)) + 1;
						orderDetail.setQty(qty);
						double actualPrice = (product.price * 1.0) + product.price
								* (margin * 1.0 / 100.0);
						actualPrice = actualPrice
								- (actualPrice * (discount * 1.0 / 100.0));
						amount += (qty * actualPrice);
						orderDetailList.add(orderDetail);
					}
					order.setSubTotal((int) amount);
					order.setStaffId("neeraj");
					int orderId = insertOrder(order, template, calendar);
					insertOrderDetail(orderDetailList, orderId, template);
				}
			}
		}
		int numCustomers = getNumCustomers(9);
		int tillNowCount = 0;
		for(int i=1; i< 1805; i++)
		{
			if(isCustomerDone[i]) {
				continue;
			}
			tillNowCount++;
			if(tillNowCount > numCustomers) {
				break;
			}
			int custId = i;
			isCustomerDone[custId] = true;
			int numTransactions = getNumTransactions(9);
			for(int j=0; j< numTransactions; j++)
			{
				calendar.set(2015, (int)(Math.random()*12), (int)(Math.random()*29), (int)(Math.random()*9) + 10, (int)(Math.random()*60), 0);
				calendar.add(Calendar.MINUTE, 10);
				int itemsCount = ((int) (Math.random() * 10)) + 1;
				OrderDto order = new OrderDto();
				order.setCustId(custId);
				order.setGcDiscount(0);
				order.setStaffId("");
				order.setStoreId("delhi");

				List<OrderDetailDto> orderDetailList = new LinkedList<OrderDetailDto>();
				double amount = 0;
				for (int k = 1; k <= itemsCount; k++) {
					int productId = getProductId(actualNumProducts);
					OrderDetailDto orderDetail = new OrderDetailDto();
					Products product = products.get(productId);
					orderDetail.setPid(productId);
					orderDetail.setCp(product.price);
					int discount = getDiscount(product, calendar.getTime());
					orderDetail.setDiscount(discount);
					int margin = getProfit(product, calendar.getTime());
					orderDetail.setMargin(margin);
					int qty = ((int) (Math.random() * 10)) + 1;
					orderDetail.setQty(qty);
					double actualPrice = (product.price * 1.0) + product.price
							* (margin * 1.0 / 100.0);
					actualPrice = actualPrice
							- (actualPrice * (discount * 1.0 / 100.0));
					amount += (qty * actualPrice);
					orderDetailList.add(orderDetail);
				}
				order.setSubTotal((int) amount);
				order.setStaffId("neeraj");
				int orderId = insertOrder(order, template, calendar);
				insertOrderDetail(orderDetailList, orderId, template);
			}
		}
		*/
		/*
		//swimming season
		Map<String, List<Products>> productMap= new HashMap<>();
		List<Products> products = new ArrayList<>();
		String sql = "SELECT a.id, c.sport_id, a.price, b.preDate, b.preProfitPercent, b.preDiscountPercent, "
				+ " b.currDate, b.currProfitPercent, b.currDiscountPercent "
				+ "FROM product_detail a, marking b, product c where a.id = b.pid and a.pid = c.pid";
		template.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String sportId = rs.getString("sport_id");
				Products product = new Products(rs.getInt("id"), rs
						.getInt("price"), rs.getDate("preDate"), rs
						.getInt("preProfitPercent"), rs
						.getInt("preDiscountPercent"), rs.getDate("currDate"),
						rs.getInt("currProfitPercent"), rs
								.getInt("currDiscountPercent"));
				products.add(product);
				List<Products> mappedProducts = productMap.get(sportId);
				if(mappedProducts == null)
				{
					mappedProducts = new ArrayList<>();
					productMap.put(sportId, mappedProducts);
				}
				mappedProducts.add(product);
			}
		});
		Calendar calendar = Calendar.getInstance();
		int date = 1;
		for (int i = 170; i < 241; i++) {
			calendar.set(2015, 0, date + i, 10, 15, 0);
			int numOrders = ((int) (Math.random() * 1)) + 2;
			for (int j = 0; j < numOrders; j++) {
				calendar.add(Calendar.MINUTE, 102);
				int custId = getCustomerId();
				int itemsCount = ((int) (Math.random() * 2)) + 1;
				OrderDto order = new OrderDto();
				order.setCustId(custId);
				order.setGcDiscount(0);
				order.setStaffId("");
				order.setStoreId("delhi");

				List<OrderDetailDto> orderDetailList = new LinkedList<OrderDetailDto>();
				double amount = 0;
				for (int k = 1; k <= itemsCount; k++) {
					List<Products> avalproducts = productMap.get("swimming");
					int productId = getProductId(avalproducts.size());
					OrderDetailDto orderDetail = new OrderDetailDto();
					Products product = avalproducts.get(productId);
					orderDetail.setPid(product.id);
					orderDetail.setCp(product.price);
					int discount = getDiscount(product, calendar.getTime());
					orderDetail.setDiscount(discount);
					int margin = getProfit(product, calendar.getTime());
					orderDetail.setMargin(margin);
					int qty = ((int) (Math.random() * 10)) + 1;
					orderDetail.setQty(qty);
					double actualPrice = (product.price * 1.0) + product.price
							* (margin * 1.0 / 100.0);
					actualPrice = actualPrice
							- (actualPrice * (discount * 1.0 / 100.0));
					amount += (qty * actualPrice);
					orderDetailList.add(orderDetail);
				}
				order.setSubTotal((int) amount);
				order.setStaffId("neeraj");
				int orderId = insertOrder(order, template, calendar);
				insertOrderDetail(orderDetailList, orderId, template);
			}
		}*/
	}
	
	private static class PriceProduct {
		String pid;
		int price;
		int discount;
		
		PriceProduct(String pid, int price, int discount)
		{
			this.pid = pid;
			this.price = price;
			this.discount = discount;
		}
	}
	
	private static int getNumCustomers(int group)
	{
		int customerCount[] = {32, 48, 102, 40, 102, 221, 270, 175, 127, 683};
		return customerCount[group];
	}
	
	private static int getNumTransactions(int group)
	{
		int transactionGroup[] = {15, 13, 11, 9, 7, 5, 4, 3, 2, 1};
		return transactionGroup[group];
	}
	private static class Events{
		Date startDate;
		Date endDate;
		String sportId;
	}

	private static int getProfit(Products product, Date date) {
		if (date.compareTo(product.preDate) < 0) {
			return 25;
		} else if (date.compareTo(product.nextDate) < 0) {
			return product.preProfit;
		} else {
			return product.currProfit;
		}
	}

	private static int getDiscount(Products product, Date date) {
		if (date.compareTo(product.preDate) < 0) {
			return 0;
		} else if (date.compareTo(product.nextDate) < 0) {
			return product.preDiscount;
		} else {
			return product.currDiscount;
		}
	}

	private static class Products {
		public Products(int id, String pid, int price, Date preDate, int preProfit,
				int preDiscount, Date nextDate, int currProfit, int currDiscount) {
			super();
			this.id = id;
			this.pid = pid;
			this.price = price;
			this.preDate = preDate;
			this.preProfit = preProfit;
			this.preDiscount = preDiscount;
			this.nextDate = nextDate;
			this.currProfit = currProfit;
			this.currDiscount = currDiscount;
		}

		int id;
		String pid;
		int price;
		Date preDate;
		int preProfit;
		int preDiscount;
		Date nextDate;
		int currProfit;
		int currDiscount;

	}

	private static class OrderEntityObject implements
			Comparable<OrderEntityObject> {
		OrderDto orderDto;
		List<OrderDetailDto> orderDetailDtoList;
		Date date;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime
					* result
					+ ((orderDetailDtoList == null) ? 0 : orderDetailDtoList
							.hashCode());
			result = prime * result
					+ ((orderDto == null) ? 0 : orderDto.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OrderEntityObject other = (OrderEntityObject) obj;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (orderDetailDtoList == null) {
				if (other.orderDetailDtoList != null)
					return false;
			} else if (!orderDetailDtoList.equals(other.orderDetailDtoList))
				return false;
			if (orderDto == null) {
				if (other.orderDto != null)
					return false;
			} else if (!orderDto.equals(other.orderDto))
				return false;
			return true;
		}

		@Override
		public int compareTo(OrderEntityObject o) {
			return this.date.compareTo(o.date);
		}
	}

	private static final String INSERT_ORDER = "INSERT INTO orders_temp"
			+ " (cust_id, sub_total, gc_discount, order_date, store_id, salestaff_id)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	private static int insertOrder(OrderDto orderDto, JdbcTemplate template,
			Calendar date) throws IOException {
		int result = 0;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int row = template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, orderDto.getCustId());
				ps.setInt(2, orderDto.getSubTotal());
				ps.setInt(3, orderDto.getGcDiscount());
				ps.setTimestamp(4,
						new java.sql.Timestamp(date.getTimeInMillis()));
				ps.setString(5, orderDto.getStoreId());
				ps.setString(6, orderDto.getStaffId());
				return ps;
			}

		}, keyHolder);

		if (row > 0)
			result = keyHolder.getKey().intValue();
		return result;
	}

	private static final String INSERT_ORDER_DETAIL = "INSERT INTO order_detail_temp"
			+ " (order_id, pid, qty, cp, margin, discount)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	private static void insertOrderDetail(
			List<OrderDetailDto> orderDetailDtoList, int orderId,
			JdbcTemplate template) throws IOException {
		try {

			template.batchUpdate(INSERT_ORDER_DETAIL,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							ps.setInt(1, orderId);
							ps.setInt(2, orderDetailDtoList.get(i).getPid());
							ps.setInt(3, orderDetailDtoList.get(i).getQty());
							ps.setInt(4, orderDetailDtoList.get(i).getCp());
							ps.setInt(5, orderDetailDtoList.get(i).getMargin());
							ps.setInt(6, orderDetailDtoList.get(i)
									.getDiscount());

						}

						@Override
						public int getBatchSize() {
							return orderDetailDtoList.size();
						}
					});

		} catch (DataAccessException e) {

			System.out.println("At OrderDetailDao :" + e);
			throw new IOException(e);
		}
	}

	private static int getBeforeDayCount() {
		return ((int) (Math.random() * 4)) + 4;
	}

	private static int getCustomerId() {
		return ((int) (Math.random() * 1800)) + 1;
	}

	private static int getProductId(int len) {
		return ((int) (Math.random() * (len-1))) + 1;
	}

	private static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	private static int getProfitId() {
		return (int) (Math.random() * 4) + 1;
	}

	private static int getDiscount() {
		int discounts[] = { 0, 15, 30, 50 };
		return discounts[(int) (Math.random() * 4)];
	}

	private static int getWarningQty() {
		return 5;
	}

	private static int getQty() {
		return 100;
	}

	private static String getStoreId() {
		return "delhi";
	}

	/**
	 * Returns a DataSource object for connection to the database.
	 * 
	 * @return a DataSource.
	 */
	private static DataSource getDataSource() {
		//
		// Creates a new instance of DriverManagerDataSource and sets
		// the required parameters such as the Jdbc Driver class,
		// Jdbc URL, database user name and password.
		//
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(SpringJdbcDataSource.DRIVER);
		dataSource.setUrl(SpringJdbcDataSource.JDBC_URL);
		dataSource.setUsername(SpringJdbcDataSource.USERNAME);
		dataSource.setPassword(SpringJdbcDataSource.PASSWORD);
		return dataSource;
	}
}
