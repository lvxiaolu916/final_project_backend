package com.training.backend.service;

import com.training.backend.entity.StockTrainsaction;
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
    public void TradingBuyTests() {


        StockTrainsaction stockTrainsaction = new StockTrainsaction();

        stockTrainsaction.setTrainsactionStatus(Constant.BUY);
        stockTrainsaction.setStockId(1);
        stockTrainsaction.setUserId(1);
        stockTrainsaction.setVolume(100);
        stockTrainsaction.setCreateTime(new Date());


        Assert.assertEquals(stockTradingService.stockTrading(stockTrainsaction), Constant.SUCCESS);
    }

    @Test
    public void TradingSellTests() {

        StockTrainsaction stockTrainsaction = new StockTrainsaction();

        stockTrainsaction.setTrainsactionStatus(Constant.SELL);
        stockTrainsaction.setStockId(1);
        stockTrainsaction.setUserId(1);
        stockTrainsaction.setVolume(100);
        stockTrainsaction.setCreateTime(new Date());
        Assert.assertEquals(stockTradingService.stockTrading(stockTrainsaction), Constant.SUCCESS);
    }


}
