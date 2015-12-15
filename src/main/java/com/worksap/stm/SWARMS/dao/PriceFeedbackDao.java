package com.worksap.stm.SWARMS.dao;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.worksap.stm.SWARMS.dto.PriceFeedbackDto;
import com.worksap.stm.SWARMS.entity.ComparativePrices;
import com.worksap.stm.SWARMS.entity.PriceComparisonEntity;
import com.worksap.stm.SWARMS.entity.ProductMarkingEntity;
import com.worksap.stm.SWARMS.entity.UpdatePriceEntity;

@Repository
public class PriceFeedbackDao {
	@Autowired
	private JdbcTemplate template;

	private static final String INSERT_FEEDBACK = "INSERT INTO price_feedback "
			+ " (pid, customer_id, date_added, higher_than_amazon, higher_than_ebay, higher_than_others)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	private static final String GET_FEEDBACKS = "SELECT * FROM price_feedback WHERE date_added = ? ORDER BY resolved ASC";

	private static final String RESOLVE_FEEDBACK = "UPDATE price_feedback SET resolved = 1, date_resolved = ? WHERE id=?";

	private static final String GET_COMPARATIVE_PRICES = "SELECT * from price_comparisons WHERE pid=?";

	private static final String GET_UPDATE_PRICE_ENTITY = "SELECT a.pid, a.procurment_price, a.mrp, a.discount_percent, "
			+ "b.our_price, b.amazon_price, b.ebay_price from profit_marking a, price_comparisons b where a.pid = b.pid and "
			+ "a.end_date IS NULL and a.pid=?";

	private static final String UPDATE_PROFIT_MARKING = "UPDATE profit_marking SET end_date = ? WHERE pid = ? and end_date IS NULL";

	private static final String INSERT_NEW_PROFIT_MARKING = "INSERT INTO profit_marking values (?,?,?,?,?,?,?)";

	private static final String UPDATE_OUR_PRICE = "UPDATE price_comparisons SET our_price=? where pid=?";

	private static final String GET_ALL_PRICE_COMPARISONS = "SELECT a.pid, a.procurment_price, a.mrp, a.discount_percent, "
			+ "b.our_price, b.amazon_price, b.ebay_price from profit_marking a, price_comparisons b where a.pid = b.pid and "
			+ "a.end_date IS NULL";

	private static final String GET_ALL_PROFIT_MARKINGS = "SELECT pid, start_date, end_date, procurment_price, mrp, discount_percent "
			+ "from profit_marking WHERE pid= ? ORDER BY start_date DESC";

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public void insert(PriceFeedbackDto feedback) {
		try {
			template.update(INSERT_FEEDBACK, (ps) -> {
				ps.setString(1, feedback.getPid());
				ps.setInt(2, feedback.getCustomerId());
				ps.setDate(3, new java.sql.Date(feedback.getDateAdded()
						.getTime()));
				ps.setBoolean(4, feedback.isHigherThanAmazon());
				ps.setBoolean(5, feedback.isHigherThanEbay());
				ps.setString(6, feedback.getHigherThanOthers());
			});
		} catch (DataAccessException e) {
			System.out.println("At FeedbackInsert :" + e);
		}
	}

	public List<PriceFeedbackDto> getTodaysFeedback() {
		return template.query(
				GET_FEEDBACKS,
				(rs, rownum) -> {
					return new PriceFeedbackDto(rs.getInt("id"), rs
							.getString("pid"), rs.getInt("customer_id"), rs
							.getDate("date_added"),
							rs.getDate("date_resolved"), rs
									.getBoolean("higher_than_amazon"), rs
									.getBoolean("higher_than_ebay"), rs
									.getString("higher_than_others"), rs
									.getBoolean("resolved"));
				}, dateFormat.format(new java.util.Date()));
	}

	public void resolveFeedback(int feedbackId) {
		template.update(RESOLVE_FEEDBACK,
				new Object[] { dateFormat.format(new java.util.Date()),
						feedbackId });
	}

	public ComparativePrices getComparativePrices(String pid) {
		List<ComparativePrices> prices = template.query(
				GET_COMPARATIVE_PRICES,
				(rs, rownum) -> {
					return new ComparativePrices(rs.getString("pid"), rs
							.getInt("our_price"), rs.getInt("amazon_price"), rs
							.getInt("ebay_price"));
				}, pid);
		return prices.get(0);
	}

	public UpdatePriceEntity getUpdatePriceEntity(String pid) {
		List<UpdatePriceEntity> updatePrices = template.query(
				GET_UPDATE_PRICE_ENTITY,
				(rs, rownum) -> {
					return new UpdatePriceEntity(rs.getString("pid"), rs
							.getInt("procurment_price"), rs.getInt("mrp"), rs
							.getInt("discount_percent"),
							rs.getInt("our_price"), rs.getInt("amazon_price"),
							rs.getInt("ebay_price"));
				}, pid);
		return updatePrices.get(0);
	}

	public void updateDiscountPercent(String pid, int newDiscount,
			int procurment_price, int mrp) {
		template.update(UPDATE_PROFIT_MARKING,
				new Object[] { dateFormat.format(new java.util.Date()), pid });
		template.update(INSERT_NEW_PROFIT_MARKING, new Object[] { pid,
				dateFormat.format(new java.util.Date()), null,
				procurment_price, mrp, 0, newDiscount });
		int ourPrice = mrp - (mrp * newDiscount / 100);
		template.update(UPDATE_OUR_PRICE, new Object[] { ourPrice, pid });
	}

	public List<PriceComparisonEntity> getAllPriceComparisons() {
		return template.query(
				GET_ALL_PRICE_COMPARISONS,
				(rs, rownum) -> {
					return new PriceComparisonEntity(rs.getString("pid"), rs
							.getInt("procurment_price"), rs.getInt("mrp"), rs
							.getInt("discount_percent"),
							rs.getInt("our_price"), rs.getInt("amazon_price"),
							rs.getInt("ebay_price"));
				});
	}

	public List<ProductMarkingEntity> getAllPreviousDiscounts(String pid) {
		// TODO Auto-generated method stub
		return template.query(
				GET_ALL_PROFIT_MARKINGS,
				(rs, rownum) -> {
					Date startDate = rs.getDate("start_date");
					Date endDate = rs.getDate("end_date");
					Date tempEndDate = endDate != null ? endDate : new Date();
					long diff = 1 + Math.round((tempEndDate.getTime() - startDate
							.getTime()) / (double) 86400000);
					long itemsSold = diff / ((int) (Math.random() + 1) * 5);
					int mrp = rs.getInt("mrp");
					int discount = rs.getInt("discount_percent");
					int sellingPrice = mrp - mrp * discount / 100;
					return new ProductMarkingEntity(rs.getString("pid"),
							startDate, endDate, rs.getInt("procurment_price"),
							mrp, discount,
							(int) (sellingPrice * itemsSold / diff));
				}, pid);
	}

	public int updatePriceComparisonTable() {
		List<PriceProduct> products = new ArrayList<>();
		String sql = "SELECT pid, mrp, discount_percent from profit_marking where end_date IS NULL";
		template.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				PriceProduct product = new PriceProduct(rs.getString("pid"), rs
						.getInt("mrp"), rs.getInt("discount_percent"));
				products.add(product);
			}
		});
		double variations[] = { 0.1, -0.1, 0.15, -0.15, 0.2, -0.2 };
		String sql1 = "REPLACE INTO price_comparisons values(?,?,?,?)";
		int count = 0;
		for (PriceProduct product : products) {
			int actPrice = product.price
					- (int) (product.price * 1.0 * (product.discount * 1.0 / 100.0));
			int amazonPrice = (int) (actPrice - actPrice
					* (variations[(int) (Math.random() * 6)]));
			int ebayPrice = (int) (actPrice - actPrice
					* (variations[(int) (Math.random() * 6)]));
			if (actPrice > amazonPrice || actPrice > ebayPrice) {
				count++;
			}
			template.update(sql1, new Object[] { product.pid, actPrice,
					amazonPrice, ebayPrice });
		}
		return count++;
	}

	private static class PriceProduct {
		String pid;
		int price;
		int discount;

		PriceProduct(String pid, int price, int discount) {
			this.pid = pid;
			this.price = price;
			this.discount = discount;
		}
	}
}
