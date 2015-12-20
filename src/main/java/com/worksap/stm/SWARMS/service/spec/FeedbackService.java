package com.worksap.stm.SWARMS.service.spec;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksap.stm.SWARMS.dto.PriceFeedbackDto;
import com.worksap.stm.SWARMS.dto.SetBestPriceReturnDto;
import com.worksap.stm.SWARMS.entity.ComparativePrices;
import com.worksap.stm.SWARMS.entity.PriceComparisonEntity;
import com.worksap.stm.SWARMS.entity.ProductMarkingEntity;
import com.worksap.stm.SWARMS.entity.UpdatePriceEntity;

@Service
public interface FeedbackService {
	void insert(PriceFeedbackDto feedback);
	List<PriceFeedbackDto> getTodaysFeedback();
	void resolveFeedback(int feedbackId);
	ComparativePrices getComparativePrices(String pid);
	UpdatePriceEntity getUpdatePriceEntity(String pid);
	void updateDiscountPercent(String pid, int newDiscount, int procurmentPrice, int mrp);
	public List<PriceComparisonEntity> getAllPriceComparisons();
	List<ProductMarkingEntity> getAllPreviousDiscounts(String pid);
	int refreshPriceComparisons();
	SetBestPriceReturnDto setBestPrices();
}
