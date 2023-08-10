package com.training.backend.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RealTimeStockServiceTests {

    @Autowired
    RealTimeStockService realTimeStockService;

    @Test
    public void findRealTimeStockAllTest(){
        Assertions.assertNotEquals(realTimeStockService.findRealTimeStockAll(),0);
    }

    @Test
    public void findRealTimeStockByStockIdTest(){
        Assertions.assertNotEquals(realTimeStockService.findRealTimeStockByStockId(1),0);
    }

    @Test
    public void findRealTimeStockByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockService.findRealTimeStockByStockName("abc"),0);
    }

    @Test
    public void setPriceByStockIdTest(){
        Assertions.assertNotEquals(realTimeStockService.setPriceByStockId(1, BigDecimal.valueOf(12.2)),0);
    }

    @Test
    public void setPriceByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockService.setPriceByStockName("abc",BigDecimal.valueOf(7.2)),0);
    }

    @Test
    public void setMarginByStockIdTest(){
        Assertions.assertNotEquals(realTimeStockService.setMarginByStockId(1,20),0);
    }

    @Test
    public void setMarginByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockService.setMarginByStockName("abc",6),0);
    }

    @Test
    public void setFluctuationByStockIdTest(){
        Assertions.assertNotEquals(realTimeStockService.setFluctuationByStockId(1,BigDecimal.valueOf(2.2)),0);
    }

    @Test
    public void setFluctuationByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockService.setFluctuationByStockName("abc",BigDecimal.valueOf(1.1)),0);
    }

}
