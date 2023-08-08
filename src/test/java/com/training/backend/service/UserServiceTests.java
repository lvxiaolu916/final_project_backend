package com.training.backend.service;

import com.training.backend.BackendApplication;
import com.training.backend.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void findUserPrincipalHoldingsByUserIdTest(){
        Assert.assertEquals(8000,userService.findUserPrincipalHoldingsByUserId(1),0.1);
    }

    @Test
    public void findUserPositionByUserIdTest(){
        Assert.assertNotNull(userService.findUserPositionByUserId(1));
    }

    @Test
    public void findStockTrainsactionByUserId(){
        Assert.assertNotNull(userService.findStockTrainsactionByUserId(1));
    }

    @Test
    public void setPrincipalHoldingsByUserId(){
        Assert.assertEquals(1,userService.setPrincipalHoldingsByUserId(1,9000));
    }

    @Test
    public void addUser(){
        User user = new User();
        user.setUserName("xl");
        user.setUserSex("female");
        user.setPrincipalHoldings(10000);
        Assert.assertEquals(1,userService.addUser(user));
    }
}
