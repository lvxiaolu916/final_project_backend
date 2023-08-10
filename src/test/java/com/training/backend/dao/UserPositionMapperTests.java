package com.training.backend.dao;

import com.training.backend.BackendApplication;
import com.training.backend.entity.User;
import com.training.backend.entity.UserPosition;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest(classes = BackendApplication.class)
public class UserPositionMapperTests {
    @Autowired
    private UserPositionMapper userPositionMapper;


    @Test
    public void insertUserPositionTest(){
        UserPosition userPosition = new UserPosition();
        userPosition.setUserId(2);
        userPosition.setStockId(1);
        userPosition.setVolume(100);
        userPosition.setPrincipalInput(BigDecimal.valueOf(1000));
        Assertions.assertEquals(1,userPositionMapper.insertUserPosition(userPosition));
    }

    @Test
    public void findVolumeAndPrincipalInputTest(){
        Assertions.assertNotNull(userPositionMapper.selectUserPositionByUserIdAndStockId(1,1));
    }

    @Test
    public void findUserPositionByUserIdTest(){
        Assertions.assertNotNull(userPositionMapper.selectUserPositionListByUserId(1));
    }

    @Test
    public void updateUserPositionTest(){
        UserPosition userPosition = new UserPosition();
        userPosition.setUserId(1);
        userPosition.setStockId(1);
        userPosition.setVolume(1000);
        userPosition.setPrincipalInput(BigDecimal.valueOf(10000));
        Assertions.assertEquals(1,userPositionMapper.updateUserPositionByUserPosition(userPosition));
    }

    @Test
    public void deleteUserPositionTest(){
        Assertions.assertEquals(1,userPositionMapper.deleteUserPositionByUserIdAndStockId(1,1));
    }
}
