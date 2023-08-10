package com.training.backend.dao;

import com.training.backend.entity.RealTimeStock;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface RealTimeStockMapper {

	int insertRealTimeStock(RealTimeStock realTimeStock);
	List<RealTimeStock> selectRealTimeStockAll();
	
	RealTimeStock selectRealTimeStockByStockId(int stockId);

	RealTimeStock selectRealTimeStockByStockName(String stockName);
	
	int updatePriceByStockId(int stockId, BigDecimal currentPrice);
	
	int updatePriceByStockName(String stockName, BigDecimal currentPrice);
	
    int updateMarginByStockId(int stockId, int stockMargin);
	
	int updateMarginByStockName(String stockName, int stockMargin);
	
    int updateFluctuationByStockId(int stockId, BigDecimal fluctuation);
	
	int updateFluctuationByStockName(String stockName, BigDecimal fluctuation);
	

}
