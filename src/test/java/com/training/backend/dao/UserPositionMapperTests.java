package com.training.backend.dao;

import com.training.backend.BackendApplication;
import com.training.backend.entity.User;
import com.training.backend.entity.UserPosition;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BackendApplication.class)
public class UserPositionMapperTests {
    @Autowired
    private UserPositionMapper userPositionMapper;

    @Test
    public void insertUserPositionTest(){
        UserPosition userPosition = new UserPosition();
        userPosition.setUserId(1);
        userPosition.setStockId(1);
        userPosition.setVolume(100);
        userPosition.setPrincipalInput(1000);
        Assertions.assertEquals(1,userPositionMapper.insertUserPosition(userPosition));
    }

    @Test
    public void findVolumeAndPrincipalInputTest(){
        Assertions.assertNotNull(userPositionMapper.findVolumeAndPrincipalInput(1,1));
    }

    @Test
    public void findUserPositionByUserIdTest(){
        Assertions.assertNotNull(userPositionMapper.findUserPositionByUserId(1));
    }

    @Test
    public void updateUserPositionTest(){
        UserPosition userPosition = new UserPosition();
        userPosition.setUserId(1);
        userPosition.setStockId(1);
        userPosition.setVolume(1000);
        userPosition.setPrincipalInput(10000);
        Assertions.assertEquals(1,userPositionMapper.updateUserPosition(userPosition));
    }

    @Test
    public void deleteUserPositionTest(){
        Assertions.assertEquals(1,userPositionMapper.deleteUserPosition(1,1));
    }
}
