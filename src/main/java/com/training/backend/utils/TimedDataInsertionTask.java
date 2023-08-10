package com.training.backend.utils;

import com.training.backend.dao.RealTimeStockMapper;
import com.training.backend.dao.StockDetailsMapper;
import com.training.backend.entity.RealTimeStock;
import com.training.backend.entity.StockDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class TimedDataInsertionTask {
    @Autowired
    private RealTimeStockMapper realTimeStockMapper;

    @Autowired
    private StockDetailsMapper stockDetailsMapper;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(TimedDataInsertionTask.class);

    @Autowired
    //线程池
    private ThreadPoolExecutor executor;
    @Scheduled(cron = "0/10 * * * * ?") //每隔20秒执行一次
    public void  addList(){

        logger.debug("task has been triggered");

        Thread thread = new Thread(() -> {

            List<StockDetails> stockDetailsList = new ArrayList<>();

            //add 20 stocks
            for(int stockId = 1; stockId <= 20; stockId++) {

                BigDecimal currentPrice = BigDecimal.valueOf(Math.random()*100);

                StockDetails stockDetails = new StockDetails();
                stockDetails.setStockName("test_"+String.valueOf(stockId));
                stockDetails.setStockId(stockId);
                stockDetails.setTime(new Date());
                stockDetails.setPrice(currentPrice);

                if ( realTimeStockMapper.selectRealTimeStockByStockId(stockId) == null){
                    RealTimeStock realTimeStock = new RealTimeStock();
                    realTimeStock.setStockId(stockId);
                    realTimeStock.setStockName("test_"+String.valueOf(stockId));
                    realTimeStock.setCurrentPrice(currentPrice);
                    realTimeStock.setFluctuation(BigDecimal.valueOf(0));
                    realTimeStockMapper.insertRealTimeStock(realTimeStock);
                }
                else {
                    BigDecimal oldPrice = realTimeStockMapper.selectRealTimeStockByStockId(stockId).getCurrentPrice();
                    realTimeStockMapper.updatePriceByStockId(stockId,currentPrice);
                    realTimeStockMapper.updateFluctuationByStockId(stockId,(currentPrice.subtract(oldPrice)).divide(oldPrice, 6,RoundingMode.HALF_UP).setScale(2,RoundingMode.HALF_UP));
                }

                stockDetailsList.add(stockDetails);
               }

            stockDetailsMapper.insertBatchStocks(stockDetailsList);

            });


        try {
            executor.execute(thread);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}



