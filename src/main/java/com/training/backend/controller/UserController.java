package com.training.backend.controller;

import com.training.backend.entity.Portfolio;
import com.training.backend.entity.RealTimeStock;
import com.training.backend.entity.UserPosition;
import com.training.backend.service.RealTimeStockService;
import com.training.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RealTimeStockService realTimeStockService;

    @GetMapping("/getUserPrincipleHoldings/{userId}")
    public BigDecimal getUserPrincipleHoldings(@PathVariable int userId){
        return userService.findUserPrincipalHoldingsByUserId(userId);
    }

    @GetMapping("/getPortfolioList/{userId}")
    public List<Portfolio> getPortfolioList(@PathVariable int userId){
        //store all portfolio
        List<Portfolio> result = new ArrayList<>();
        //get stockId
        List<UserPosition> userPositions = userService.findUserPositionByUserId(userId);
        //get stockName
        for(UserPosition u : userPositions){
            RealTimeStock realTimeStock = realTimeStockService.findRealTimeStockByStockId(u.getStockId());
            UserPosition userPosition = userService.findUserPositionByUserIdAndStockId(u.getUserId(),u.getStockId());
            BigDecimal profits = realTimeStock.getCurrentPrice().multiply(BigDecimal.valueOf(u.getVolume())).subtract(userPosition.getPrincipalInput());
            BigDecimal returnRates = profits.divide(userPosition.getPrincipalInput(),6, RoundingMode.HALF_UP);

            Portfolio portfolio = new Portfolio();
            portfolio.setStockId(u.getStockId());
            portfolio.setStockName(realTimeStock.getStockName());
            portfolio.setCurrentPrice(realTimeStock.getCurrentPrice());
            portfolio.setReturnRates(returnRates);
            portfolio.setProfits(profits);

            result.add(portfolio);
        }
        return result;
    }

}
