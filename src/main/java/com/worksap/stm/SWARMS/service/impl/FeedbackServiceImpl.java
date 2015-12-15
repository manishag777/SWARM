package com.worksap.stm.SWARMS.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dao.PriceFeedbackDao;
import com.worksap.stm.SWARMS.dto.PriceFeedbackDto;
import com.worksap.stm.SWARMS.entity.ComparativePrices;
import com.worksap.stm.SWARMS.entity.PriceComparisonEntity;
import com.worksap.stm.SWARMS.entity.ProductMarkingEntity;
import com.worksap.stm.SWARMS.entity.UpdatePriceEntity;
import com.worksap.stm.SWARMS.service.spec.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	PriceFeedbackDao feedbackDao;
	
	@Override
	public void insert(PriceFeedbackDto feedback) {
		feedbackDao.insert(feedback);
	}

	@Override
	public List<PriceFeedbackDto> getTodaysFeedback() {
		return feedbackDao.getTodaysFeedback();
	}
	
	@Override
	public void resolveFeedback(int feedbackId) {
		feedbackDao.resolveFeedback(feedbackId);
	}

	@Override
	public ComparativePrices getComparativePrices(String pid) {
		return feedbackDao.getComparativePrices(pid);
	}

	@Override
	public UpdatePriceEntity getUpdatePriceEntity(String pid) {
		return feedbackDao.getUpdatePriceEntity(pid);
	}
	
	@Override
	public void updateDiscountPercent(String pid, int newDiscount, int procurmentPrice, int mrp) {
		feedbackDao.updateDiscountPercent(pid, newDiscount, procurmentPrice, mrp);
	}
	
	@Override
	public List<PriceComparisonEntity> getAllPriceComparisons() {
		return feedbackDao.getAllPriceComparisons();
	}

	@Override
	public List<ProductMarkingEntity> getAllPreviousDiscounts(String pid) {
		return feedbackDao.getAllPreviousDiscounts(pid);
	}

	@Override
	public int refreshPriceComparisons() {
		return feedbackDao.updatePriceComparisonTable();
	}
}
