package com.training.backend.dao;


import com.training.backend.entity.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {

//    @Autowired
//    private UserMapper userMapper;
//
////    @Ignore
//    @Test
//    public void insertUserTest() {
//        User user = new User();
//        user.setUserId(1);
//        user.setUserName("jingkai");
//        user.setUserSex("male");
//        user.setPrincipalHoldings(BigDecimal.valueOf(10000));
//
//        Assert.assertEquals(userMapper.insertUser(user),1);
//    }
//
//    @Test
//    public void selectUserByUserIdTest() {
//        Assert.assertNotEquals(userMapper.selectUserByUserId(1),null);
//    }
//
//    @Test
//    public void updatePrincipalHoldingsByUserIdTest() {
//        Assert.assertEquals(userMapper.updatePrincipalHoldingsByUserId(1, BigDecimal.valueOf(102.56)),1);
//    }

}
