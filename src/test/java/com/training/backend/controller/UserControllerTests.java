package com.training.backend.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTests {
    @Autowired
    private UserController userController;

    @Test
    public void getUserPrincipleHoldingsTest(){
        Assert.assertEquals(new BigDecimal("50.0"),userController.getUserPrincipleHoldings(1));
    }

    @Test
    public void getPortfolioListTest(){
        Assert.assertNotNull(userController.getPortfolioList(1));
    }
}
