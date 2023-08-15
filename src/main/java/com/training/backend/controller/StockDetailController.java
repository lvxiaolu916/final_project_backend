package com.training.backend.controller;

import com.training.backend.entity.RealTimeStock;
import com.training.backend.entity.SingleStockDetail;
import com.training.backend.entity.StockDetails;
import com.training.backend.entity.UserPosition;
import com.training.backend.service.RealTimeStockService;
import com.training.backend.service.StockDetailsService;
import com.training.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/stock-detail")
public class StockDetailController {

    @Autowired
    UserService userService;

    @Autowired
    StockDetailsService stockDetailsService;

    @Autowired
    RealTimeStockService realTimeStockService;

    @GetMapping("/getSingleStockDetail/{stockId}")
    public SingleStockDetail getSingleStockDetail(@RequestParam(required = false) Integer userId, @PathVariable("stockId") int stockId){

        //stockVolume and principalInput
        boolean userHasThisStock = true;
        int stockVolume = 0;
        UserPosition userPosition = null;
        BigDecimal currentInterestRate = BigDecimal.valueOf(0);
        BigDecimal principalInput = BigDecimal.valueOf(0);

        try {
            userPosition = userService.findUserPositionByUserIdAndStockId(1,stockId);
        }catch (IllegalArgumentException exception) {
            userHasThisStock = false;
        }

        if (userHasThisStock) {
            stockVolume = userPosition.getVolume();
            principalInput = userPosition.getPrincipalInput();
        }

        String stockName = realTimeStockService.findRealTimeStockByStockId(stockId).getStockName();
        BigDecimal holdingPrincipal = userService.findUserPrincipalHoldingsByUserId(1);


        List<StockDetails> list = stockDetailsService.findStockDetailsByUserId(stockId,7);

        //fluctuationRate and fluctuationPrice
        BigDecimal newPrice = list.get(0).getPrice();
        BigDecimal oldPrice = list.get(1).getPrice();
        BigDecimal fluctuationRate = realTimeStockService.findRealTimeStockByStockId(stockId).getFluctuation();
        BigDecimal fluctuationPrice = fluctuationRate.multiply(oldPrice);

        //maxPrice and minPrice
        BigDecimal maxPrice = BigDecimal.valueOf(Double.MIN_VALUE);
        BigDecimal minPrice = BigDecimal.valueOf(Double.MAX_VALUE);

        for (StockDetails stockDetails : list) {
                BigDecimal curPrice = stockDetails.getPrice();
            if (curPrice.compareTo(maxPrice) > 0) {
                maxPrice = curPrice;
            }
            if (curPrice.compareTo(minPrice) < 0) {
                minPrice = curPrice;
            }
        }

        //currentInterestRate
        if (userHasThisStock) {
            currentInterestRate = ((newPrice.multiply(BigDecimal.valueOf(stockVolume))).subtract(principalInput))
                    .divide(principalInput, 6,RoundingMode.HALF_UP);
        }

        SingleStockDetail singleStockDetail = new SingleStockDetail();
        singleStockDetail.setFluctuationPrice(fluctuationPrice.setScale(2,RoundingMode.HALF_UP));
        singleStockDetail.setFluctuationRate(fluctuationRate);
        singleStockDetail.setMaxPrice(maxPrice);
        singleStockDetail.setMinPrice(minPrice);
        singleStockDetail.setHoldingVolume(stockVolume);
        singleStockDetail.setCurrentPrice(newPrice.setScale(2,RoundingMode.HALF_UP));
        singleStockDetail.setCurrentInterestRate(currentInterestRate.setScale(2,RoundingMode.HALF_UP));
        singleStockDetail.setStockName(stockName);
        singleStockDetail.setHoldingPrincipal(holdingPrincipal);

        return singleStockDetail;
    }

    @GetMapping("/getWeeklyTrendDetails/{stockId}")
    public List<BigDecimal> getWeeklyTrendDetails(@PathVariable("stockId") int stockId){
        List<StockDetails> list2 = stockDetailsService.findStockDetailsByUserId(stockId,7);

        int count=0;
        List<BigDecimal> result = new ArrayList<>();
        while(count<list2.size()){
            result.add(list2.get(count).getPrice());
            count++;
        }
        return result;
    }
}
