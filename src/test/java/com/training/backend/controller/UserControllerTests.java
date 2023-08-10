package com.training.backend.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTests {
    @Autowired
    private UserController userController;

    @Test
    public void getUserPrincipleHoldingsTest(){
        //Assert.assertEquals(50,userController.getUserPrincipleHoldings(1),0.1);
    }

    @Test
    public void getPortfolioListTest(){
        Assert.assertNotNull(userController.getPortfolioList(1));
    }
}
