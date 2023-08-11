package com.training.backend.controller;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockDetailControllerTests {
    @Autowired
    private StockDetailController stockDetailController;

    @Test
    public void getSingleStockDetailTest(){
        Assertions.assertNotNull(stockDetailController.getSingleStockDetail(1,1));
    }

    @Test
    public void getWeeklyTrendDetailsTest(){
       // Assert.assertEquals(7,stockDetailController.getWeeklyTrendDetails(1).size());
        System.out.println(stockDetailController.getWeeklyTrendDetails(1).size());
    }

}
