package com.training.backend.dao;

import com.training.backend.entity.RealTimeStock;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RealTimeStockMapperTests {


    @Autowired
    RealTimeStockMapper realTimeStockMapper;


    @Ignore
    @Test
    public void insertRealTimeStockTest(){
        RealTimeStock realTimeStock = new RealTimeStock();
        realTimeStock.setStockId(1);
        realTimeStock.setStockName("abc");
        realTimeStock.setCurrentPrice(10.1);
        realTimeStock.setStockMargin(100);
        realTimeStock.setFluctuation(1.4);

        Assert.assertEquals(realTimeStockMapper.insertRealTimeStock(realTimeStock),1);
    }

    @Test
    public void selectRealTimeStockAllTest(){
        Assertions.assertNotEquals(realTimeStockMapper.selectRealTimeStockAll(),0);
    }

    @Test
    public void selectRealTimeStockByStockIdTest(){
        Assertions.assertNotEquals(realTimeStockMapper.selectRealTimeStockByStockId(1),0);
    }

    @Test
    public void selectRealTimeStockByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockMapper.selectRealTimeStockByStockName("abc"),0);
    }

    @Test
    public void updatePriceByStockIdTest(){
        Assertions.assertNotEquals(realTimeStockMapper.updatePriceByStockId(1,20.3),0);
    }

    @Test
    public void updatePriceByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockMapper.updatePriceByStockName("abc",15.3),0);
    }

    @Test
    public void updateMarginByStockIdTest(){
        Assertions.assertNotEquals(realTimeStockMapper.updateMarginByStockId(1,15),0);
    }

    @Test
    public void updateMarginByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockMapper.updateMarginByStockName("abc",6),0);
    }

    @Test
    public void updateFluctuationByStockIdTest(){
        Assertions.assertNotEquals(realTimeStockMapper.updateFluctuationByStockId(1,4.3),0);
    }

    @Test
    public void updateFluctuationByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockMapper.updateFluctuationByStockName("abc",2.1),0);
    }

}
