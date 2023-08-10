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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    Map<String, String> stockTrading(@RequestBody List<StockTrainsaction> tradeList){

        int resStatus = Constant.SUCCESS;

        //need to get total value, and benefit from
        BigDecimal initalUserPrincipal = userService.findUserPrincipalHoldingsByUserId(1);
        BigDecimal finalUserPrincipal = BigDecimal.valueOf(0);
        BigDecimal totalValue = BigDecimal.valueOf(0);
        BigDecimal benefit = BigDecimal.valueOf(0);


        List<UserPosition> userPositionList = userService.findUserPositionByUserId(1);

        //start to trading
        for (StockTrainsaction item : tradeList) {

            UserPosition specifyUserPosition = findSpecifyUserPosition(userPositionList, item.getUserId(), item.getStockId());

            if (item.getTrainsactionStatus() == Constant.SELL) {

//                BigDecimal currentValue = BigDecimal.valueOf(item.getVolume()).multiply(realTimeStockService.findRealTimeStockByStockId(item.getStockId()).getCurrentPrice());
//                BigDecimal oldValue =specifyUserPosition.getPrincipalInput().multiply(
//                        (BigDecimal.valueOf(item.getVolume()).divide(
//                                BigDecimal.valueOf(specifyUserPosition.getVolume()) ,3, RoundingMode.HALF_UP)
//
//                        ));
//
//                System.out.println(currentValue);
//                System.out.println(oldValue);
//
//                benefit = benefit.add(currentValue.subtract(oldValue));
//                System.out.println(benefit);


                benefit = benefit.add(
                        BigDecimal.valueOf(item.getVolume()).multiply(
                                 realTimeStockService.findRealTimeStockByStockId(item.getStockId()).getCurrentPrice()).subtract(
                        specifyUserPosition.getPrincipalInput().multiply(
                                BigDecimal.valueOf(item.getVolume()).divide(
                                        BigDecimal.valueOf(specifyUserPosition.getVolume()),6,
                                        RoundingMode.HALF_UP
                                )
                        )
                        )
                );
            }

            resStatus = stockTradingService.stockTrading(item);

            if (resStatus != Constant.SUCCESS) {
                break;
            }
        }

        finalUserPrincipal = userService.findUserPrincipalHoldingsByUserId(1);
        totalValue = finalUserPrincipal.subtract(initalUserPrincipal);

        Map<String, String> resMap = new HashMap<>();
        resMap.put("totalValue", totalValue.toString());
        resMap.put("benefit", benefit.setScale(2,RoundingMode.HALF_UP).toString());

        if (resStatus == Constant.SUCCESS){
            resMap.put("status","SUCCESS");
        }
        else if (resStatus == Constant.USER_NOT_ENOUGH_PRINCIPAL){
            resMap.put("status","USER_NOT_ENOUGH_PRINCIPAL");
        }
        else if (resStatus == Constant.USER_POSITION_IS_NULL_WHEN_SELL){
            resMap.put("status","USER_POSITION_IS_NULL_WHEN_SELL");
        }else {
            resMap.put("status","USER_POSITION_NOT_ENOUGH_VOLUEM");
        }



        return resMap;

    }
}
