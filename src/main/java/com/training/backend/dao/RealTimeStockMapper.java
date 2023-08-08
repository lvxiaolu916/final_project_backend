package com.training.backend.dao;

import com.training.backend.entity.RealTimeStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RealTimeStockMapper {

	int insertRealTimeStock(RealTimeStock realTimeStock);
	List<RealTimeStock> selectRealTimeStockAll();
	
	RealTimeStock selectRealTimeStockByStockId(int stockId);

	RealTimeStock selectRealTimeStockByStockName(String stockName);
	
	int updatePriceByStockId(int stockId, double currentPrice);
	
	int updatePriceByStockName(String stockName, double currentPrice);
	
    int updateMarginByStockId(int stockId, int stockMargin);
	
	int updateMarginByStockName(String stockName, int stockMargin);
	
    int updateFluctuationByStockId(int stockId, double fluctuation);
	
	int updateFluctuationByStockName(String stockName, double fluctuation);
	

}
