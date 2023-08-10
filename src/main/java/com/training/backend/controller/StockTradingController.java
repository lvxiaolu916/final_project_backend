package com.training.backend.controller;

import com.training.backend.entity.StockTrainsaction;
import com.training.backend.entity.UserPosition;
import com.training.backend.service.RealTimeStockService;
import com.training.backend.service.StockTradingService;
import com.training.backend.service.UserService;
import com.training.backend.utils.Constant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stockTrading")
public class StockTradingController {

    @Autowired
    private StockTradingService stockTradingService;

    @Autowired
    private UserService userService;

    @Autowired
    private RealTimeStockService realTimeStockService;



    private UserPosition findSpecifyUserPosition(List<UserPosition> userPositionList, int userId, int stockId) {
        for (UserPosition userPosition : userPositionList) {
            if (userPosition.getUserId() == userId && userPosition.getStockId() == stockId) {
                return userPosition;
            }
        }
        return null;
    }

    @RequestMapping(path = "/trading", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Double> stockTrading(@RequestBody List<StockTrainsaction> tradeList){

        //need to get total value, and benefit from

        double initalUserPrincipal = userService.findUserPrincipalHoldingsByUserId(1);
        double finalUserPrincipal = 0;
        double totalValue = 0;
        double benefit = 0;


        List<UserPosition> userPositionList = userService.findUserPositionByUserId(1);

        //start to trading
        for (StockTrainsaction item : tradeList) {

            UserPosition specifyUserPosition = findSpecifyUserPosition(userPositionList, item.getUserId(), item.getStockId());

            if (item.getTrainsactionStatus() == Constant.SELL) {
                benefit += item.getVolume() * realTimeStockService.findRealTimeStockByStockId(item.getStockId()).getCurrentPrice() -
                        specifyUserPosition.getPrincipalInput() * ((double) item.getVolume() / specifyUserPosition.getVolume());
            }

            stockTradingService.stockTrading(item);

        }

        finalUserPrincipal = userService.findUserPrincipalHoldingsByUserId(1);
        totalValue = finalUserPrincipal - initalUserPrincipal;

        Map<String, Double> resMap = new HashMap<>();
        resMap.put("totalValue", totalValue);
        resMap.put("benefit", benefit);

        return resMap;

    }
}
