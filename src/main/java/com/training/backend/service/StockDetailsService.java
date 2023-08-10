package com.training.backend.service;

import com.training.backend.dao.StockDetailsMapper;
import com.training.backend.entity.StockDetails;
import com.training.backend.entity.StockTrainsaction;
import com.training.backend.entity.User;
import com.training.backend.entity.UserPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockDetailsService {

    @Autowired
    private StockDetailsMapper stockDetailsMapper;


    public List<StockDetails> findStockDetailsByUserId(int stockId, int limit){
        return stockDetailsMapper.selectStocksByStockIdAndLimit(stockId,limit);
    }

    public int addAStock(StockDetails stockDetails){
        return stockDetailsMapper.insertAStock(stockDetails);
    }

    public int addBatchStock(List<StockDetails> stockDetailsList){
        return stockDetailsMapper.insertBatchStocks(stockDetailsList);
    }



}
