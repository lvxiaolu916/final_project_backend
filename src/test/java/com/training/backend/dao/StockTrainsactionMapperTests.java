package com.training.backend.dao;

import com.training.backend.entity.StockTrainsaction;
import com.training.backend.entity.User;
import lombok.Data;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockTrainsactionMapperTests {

    @Autowired
    StockTrainsactionMapper stockTrainsactionMapper;

    @Ignore
    @Test
    public void insertStockTrainsactionTest() {

        StockTrainsaction stockTrainsaction = new StockTrainsaction();
        stockTrainsaction.setUserId(1);
        stockTrainsaction.setStockId(1);
        stockTrainsaction.setCreateTime(new Date());
        stockTrainsaction.setTrainsactionStatus(0);
        stockTrainsaction.setVolume(50);

        Assert.assertEquals(stockTrainsactionMapper.insertStockTrainsaction(stockTrainsaction),1);
    }

    @Test
    public void selectUserByUserIdTest() {
        Assert.assertNotEquals(stockTrainsactionMapper.selectStockTrainsactionByUserId(1),null);
        System.out.println(stockTrainsactionMapper.selectStockTrainsactionByUserId(1));
    }

}
