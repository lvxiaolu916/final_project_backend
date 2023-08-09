package com.training.backend.service;

import com.training.backend.entity.StockDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockDetailsServiceTests {

    @Autowired
    StockDetailsService stockDetailsService;

    @Test
    public void insertAStockTest() {
        StockDetails stockDetails = new StockDetails();
        stockDetails.setStockId(1);
        stockDetails.setStockName("test1");
        stockDetails.setTime(new Date());
        stockDetails.setPrice(600);
        stockDetailsService.addAStock(stockDetails);
    }

    @Test
    public void insertBatchStockTest() {

        List<StockDetails> list = new ArrayList<>();


        StockDetails stockDetails = new StockDetails();
        stockDetails.setStockId(1);
        stockDetails.setStockName("test1");
        stockDetails.setTime(new Date());
        stockDetails.setPrice(600);

        StockDetails stockDetails1 = new StockDetails();
        stockDetails1.setStockId(2);
        stockDetails1.setStockName("test2");
        stockDetails1.setTime(new Date());
        stockDetails1.setPrice(300);

        list.add(stockDetails);
        list.add(stockDetails1);

        stockDetailsService.addBatchStock(list);
    }

    @Test
    public void selectStocksByStockIdAndLimitTest() {

        Assert.assertNotEquals(null,stockDetailsService.findUserPositionByUserId(1,1));
    }
}
