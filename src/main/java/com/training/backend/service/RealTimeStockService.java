package com.training.backend.service;

import com.training.backend.dao.RealTimeStockMapper;
import com.training.backend.entity.RealTimeStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RealTimeStockService {

    @Autowired
    private RealTimeStockMapper realTimeStockMapper;

    private static final Logger logger = LoggerFactory.getLogger(StockTradingService.class);

    public List<RealTimeStock> findRealTimeStockAll(){
        return realTimeStockMapper.selectRealTimeStockAll();
    }
    
    public RealTimeStock findRealTimeStockByStockId(int stockId){
        return realTimeStockMapper.selectRealTimeStockByStockId(stockId);
    }
    
    public RealTimeStock findRealTimeStockByStockName(String stockName){
        return realTimeStockMapper.selectRealTimeStockByStockName(stockName);
    }
    
    public int setPriceByStockId(int stockId, double newPrice){
        RealTimeStock currealTimeStock = realTimeStockMapper.selectRealTimeStockByStockId(stockId);
        
        if(currealTimeStock!=null){
            return realTimeStockMapper.updatePriceByStockId(stockId,newPrice);
        }
        else{
            logger.error("StockId is null");
            throw new IllegalArgumentException("StockId not found: "+stockId);
        }
    }

    public int setPriceByStockName(String stockName, double newPrice){
        RealTimeStock currealTimeStock = realTimeStockMapper.selectRealTimeStockByStockName(stockName);

        if(currealTimeStock!=null){
            return realTimeStockMapper.updatePriceByStockName(stockName,newPrice);
        }
        else{
            logger.error("StockName is null");
            throw new IllegalArgumentException("StockName not found: "+stockName);
        }
    }

    public int setMarginByStockId(int stockId, int newMargin){
        RealTimeStock currealTimeStock = realTimeStockMapper.selectRealTimeStockByStockId(stockId);

        if(currealTimeStock!=null){
            return realTimeStockMapper.updateMarginByStockId(stockId,newMargin);
        }
        else{
            logger.error("StockId is null");
            throw new IllegalArgumentException("StockId not found: "+stockId);
        }
    }

    public int setMarginByStockName(String stockName, int newMargin){
        RealTimeStock currealTimeStock = realTimeStockMapper.selectRealTimeStockByStockName(stockName);

        if(currealTimeStock!=null){
            return realTimeStockMapper.updateMarginByStockName(stockName,newMargin);
        }
        else{
            logger.error("StockName is null");
            throw new IllegalArgumentException("StockName not found: "+stockName);
        }
    }

    public int setFluctuationByStockId(int stockId, double newFluctuation){
        RealTimeStock currealTimeStock = realTimeStockMapper.selectRealTimeStockByStockId(stockId);

        if(currealTimeStock!=null){
            return realTimeStockMapper.updateFluctuationByStockId(stockId,newFluctuation);
        }
        else{
            logger.error("StockId is null");
            throw new IllegalArgumentException("StockId not found: "+stockId);
        }
    }

    public int setFluctuationByStockName(String stockName, double newFluctuation){
        RealTimeStock currealTimeStock = realTimeStockMapper.selectRealTimeStockByStockName(stockName);

        if(currealTimeStock!=null){
            return realTimeStockMapper.updateFluctuationByStockName(stockName,newFluctuation);
        }
        else{
            logger.error("StockName is null");
            throw new IllegalArgumentException("StockName not found: "+stockName);
        }
    }


    
    
    

}
