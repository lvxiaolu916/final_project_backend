package com.training.backend.controller;

import com.training.backend.entity.Market;
import com.training.backend.entity.RealTimeStock;
import com.training.backend.entity.StockDetails;
import com.training.backend.service.RealTimeStockService;
import com.training.backend.service.StockDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/realTimeStock")
public class RealTimeStockController {
    @Autowired
    private RealTimeStockService realTimeStockService;
    @Autowired
    private StockDetailsService stockDetailsService;

    @GetMapping("/getMarketList")
    public List<Market> getMarketList(){
        //store all Market
        List<Market> result = new ArrayList<>();
        //get stockId,stockName,currentPrice
        List<RealTimeStock> realTimeStockAll = realTimeStockService.findRealTimeStockAll();


        for(RealTimeStock r : realTimeStockAll){
            //find the last 2 records about this stock
            List<StockDetails> stockDetails = stockDetailsService.findStockDetailsByUserId(r.getStockId(),2);
            BigDecimal oldPrice = stockDetails.get(1).getPrice();
            BigDecimal fluctuation= r.getFluctuation().multiply(oldPrice);

            Market market = new Market();
            market.setStockId(r.getStockId());
            market.setStockName(r.getStockName());
            market.setCurrentPrice(r.getCurrentPrice());
            market.setPriceFluctuation(fluctuation);
            market.setFluctuationRate(r.getFluctuation());

            result.add(market);
        }
        return result;
    }
}
