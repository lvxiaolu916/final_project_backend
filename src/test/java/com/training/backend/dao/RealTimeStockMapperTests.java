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

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RealTimeStockMapperTests {


    @Autowired
    RealTimeStockMapper realTimeStockMapper;


//    @Ignore
    @Test
    public void insertRealTimeStockTest(){
        RealTimeStock realTimeStock = new RealTimeStock();

        realTimeStock.setStockId(1);
        realTimeStock.setStockName("test1");
        realTimeStock.setCurrentPrice(BigDecimal.valueOf(5.1));
//        realTimeStock.setStockMargin(40045);
        realTimeStock.setFluctuation(BigDecimal.valueOf(-0.6));
        realTimeStockMapper.insertRealTimeStock(realTimeStock);


        realTimeStock.setStockId(2);
        realTimeStock.setStockName("test2");
        realTimeStock.setCurrentPrice(BigDecimal.valueOf(7.1));
//        realTimeStock.setStockMargin(60045);
        realTimeStock.setFluctuation(BigDecimal.valueOf(1.2));
        realTimeStockMapper.insertRealTimeStock(realTimeStock);

//        Assert.assertEquals(realTimeStockMapper.insertRealTimeStock(realTimeStock),1);
        realTimeStock.setStockId(3);
        realTimeStock.setStockName("test3");
        realTimeStock.setCurrentPrice(BigDecimal.valueOf(6.1));
//        realTimeStock.setStockMargin(64145);
        realTimeStock.setFluctuation(BigDecimal.valueOf(1.8));
        realTimeStockMapper.insertRealTimeStock(realTimeStock);

        realTimeStock.setStockId(4);
        realTimeStock.setStockName("test4");
        realTimeStock.setCurrentPrice(BigDecimal.valueOf(3.5));
//        realTimeStock.setStockMargin(20035);
        realTimeStock.setFluctuation(BigDecimal.valueOf(-4.6));
        realTimeStockMapper.insertRealTimeStock(realTimeStock);

        realTimeStock.setStockId(5);
        realTimeStock.setStockName("test5");
        realTimeStock.setCurrentPrice(BigDecimal.valueOf(17.1));
//        realTimeStock.setStockMargin(95357);
        realTimeStock.setFluctuation(BigDecimal.valueOf(-1.2));
        realTimeStockMapper.insertRealTimeStock(realTimeStock);
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
        Assertions.assertNotEquals(realTimeStockMapper.updatePriceByStockId(1,BigDecimal.valueOf(20.3)),0);
    }

    @Test
    public void updatePriceByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockMapper.updatePriceByStockName("abc",BigDecimal.valueOf(15.3)),0);
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
        Assertions.assertNotEquals(realTimeStockMapper.updateFluctuationByStockId(1,BigDecimal.valueOf(4.3)),0);
    }

    @Test
    public void updateFluctuationByStockNameTest(){
        Assertions.assertNotEquals(realTimeStockMapper.updateFluctuationByStockName("abc",BigDecimal.valueOf(2.1)),0);
    }

}
