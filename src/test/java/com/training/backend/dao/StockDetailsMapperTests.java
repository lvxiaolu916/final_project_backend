package com.training.backend.dao;

import com.training.backend.entity.StockDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockDetailsMapperTests {

//    @Autowired
//    private StockDetailsMapper stockDetailsMapper;
//
//    @Test
//    public void insertAStockTest() {
//        StockDetails stockDetails = new StockDetails();
//        stockDetails.setStockId(1);
//        stockDetails.setStockName("test1");
//        stockDetails.setTime(new Date());
//        stockDetails.setPrice(BigDecimal.valueOf(600));
//        stockDetailsMapper.insertAStock(stockDetails);
//    }
//
//    @Test
//    public void insertBatchStockTest() {
//
//        List<StockDetails> list = new ArrayList<>();
//
//
//        StockDetails stockDetails = new StockDetails();
//        stockDetails.setStockId(1);
//        stockDetails.setStockName("test1");
//        stockDetails.setTime(new Date());
//        stockDetails.setPrice(BigDecimal.valueOf(600));
//
//        StockDetails stockDetails1 = new StockDetails();
//        stockDetails1.setStockId(2);
//        stockDetails1.setStockName("test2");
//        stockDetails1.setTime(new Date());
//        stockDetails1.setPrice(BigDecimal.valueOf(300));
//
//        list.add(stockDetails);
//        list.add(stockDetails1);
//
//        stockDetailsMapper.insertBatchStocks(list);
//    }
//
//    @Test
//    public void selectStocksByStockIdAndLimitTest() {
//
//        Assert.assertNotEquals(null,stockDetailsMapper.selectStocksByStockIdAndLimit(1,1));
//        System.out.println(stockDetailsMapper.selectStocksByStockIdAndLimit(1,1));
//    }


}
