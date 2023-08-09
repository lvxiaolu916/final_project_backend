package com.training.backend.dao;

import com.training.backend.entity.StockDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockDetailsMapper {

    //Get the latest N stocks content
    List<StockDetails> selectStocksByStockIdAndLimit(int stockId, int limit);

    //insert a stock
    int insertAStock(StockDetails stockDetails);

    //insert batch stocks
    int insertBatchStocks(@Param("stockDetailsList") List<StockDetails> stockDetailsList);
}
