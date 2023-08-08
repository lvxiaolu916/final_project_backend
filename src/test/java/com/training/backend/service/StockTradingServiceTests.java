package com.training.backend.service;

import com.training.backend.utils.Constant;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockTradingServiceTests {

    @Autowired
    private StockTradingService stockTradingService;


    @Test
    public void TraindingBuyTests() {

        Map<String,Object> testMap = new HashMap<>();

        testMap.put("trainsactionStatus",Constant.BUY);
        testMap.put("time",new Date());
        testMap.put("userId",1);
        testMap.put("stockId",1);
        testMap.put("volume",100);

        Assert.assertEquals(stockTradingService.stockTrading(testMap), Constant.SUCCESS);
    }

    @Test
    public void TraindingSellTests() {

        Map<String,Object> testMap = new HashMap<>();

        testMap.put("trainsactionStatus",Constant.SELL);
        testMap.put("time",new Date());
        testMap.put("userId",1);
        testMap.put("stockId",1);
        testMap.put("volume",100);

        Assert.assertEquals(stockTradingService.stockTrading(testMap), Constant.SUCCESS);
    }


}
