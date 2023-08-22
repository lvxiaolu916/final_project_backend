package com.training.backend.service;

import com.training.backend.dao.RealTimeStockMapper;
import com.training.backend.dao.StockTrainsactionMapper;
import com.training.backend.dao.UserMapper;
import com.training.backend.dao.UserPositionMapper;
import com.training.backend.entity.RealTimeStock;
import com.training.backend.entity.StockTrainsaction;
import com.training.backend.entity.User;
import com.training.backend.entity.UserPosition;
import com.training.backend.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StockTradingService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPositionMapper userPositionMapper;

    @Autowired
    private StockTrainsactionMapper stockTrainsactionMapper;

    @Autowired
    private RealTimeStockMapper realTimeStockMapper;

    private static final Logger logger = LoggerFactory.getLogger(StockTradingService.class);


    //Judge whether the basic conditions of the transaction are met
    private int justTransaction(int trainsactionStatus, BigDecimal totalTransactionPrice, int volume, BigDecimal userPrincipal, UserPosition userPosition) {
        if (trainsactionStatus == Constant.BUY && totalTransactionPrice.compareTo(userPrincipal) == 1)
        {
            logger.error("USER_NOT_ENOUGH_PRINCIPAL");

            return Constant.USER_NOT_ENOUGH_PRINCIPAL;
        }
        else if (trainsactionStatus == Constant.SELL && userPosition != null && volume > userPosition.getVolume())
        {
            logger.error("USER_POSITION_NOT_ENOUGH_VOLUEM");

            return Constant.USER_POSITION_NOT_ENOUGH_VOLUEM;
        }
        else if (trainsactionStatus == Constant.SELL && userPosition == null)
        {

            logger.error("USER_POSITION_IS_NULL_WHEN_SELL");
//            throw new IllegalArgumentException("USER_POSITION_IS_NULL_WHEN_SELL");
            return Constant.USER_POSITION_IS_NULL_WHEN_SELL;
        }
        else {
            return Constant.SUCCESS;
        }
    }


    private void modifyUserPrincipal(int trainsactionStatus, User user, BigDecimal totalTransactionPrice) {
        BigDecimal currentUserPrincipal = BigDecimal.valueOf(0);

        if (trainsactionStatus == Constant.BUY) {
            currentUserPrincipal = user.getPrincipalHoldings().subtract(totalTransactionPrice);
        }
        else {
            currentUserPrincipal = user.getPrincipalHoldings().add(totalTransactionPrice);
        }
        userMapper.updatePrincipalHoldingsByUserId(user.getUserId(),currentUserPrincipal);
    }

    private void modifyUserPosition (StockTrainsaction stockTrainsaction, UserPosition userPosition,  BigDecimal totalTransactionPrice) {

        int trainsactinoStatus = stockTrainsaction.getTrainsactionStatus();
        int volume = stockTrainsaction.getVolume();
        int userId = stockTrainsaction.getUserId();
        int stockId = stockTrainsaction.getStockId();

        if (userPosition != null) {

            if (trainsactinoStatus == Constant.SELL && userPosition.getVolume() == volume) {

                userPositionMapper.deleteUserPositionByUserIdAndStockId(userId, stockId);
            }
            else if(trainsactinoStatus == Constant.SELL && userPosition.getVolume() != volume) {

                UserPosition userPositionTemplete = new UserPosition();
                userPositionTemplete.setUserId(userId);
                userPositionTemplete.setStockId(stockId);
                userPositionTemplete.setVolume(userPosition.getVolume()-volume);
                userPositionTemplete.setFirstStatus(Constant.NO_FIRST_BUY);

                BigDecimal rate = (BigDecimal.valueOf(userPosition.getVolume()).subtract(BigDecimal.valueOf(volume))).divide(BigDecimal.valueOf(userPosition.getVolume()), 24,RoundingMode.HALF_UP).setScale(2,RoundingMode.HALF_UP);
//                logger.info("rate is :"+String.valueOf(rate));

                userPositionTemplete.setPrincipalInput(userPosition.getPrincipalInput().multiply(rate));

                userPositionMapper.updateUserPositionByUserPosition(userPositionTemplete);
            }
            else if(trainsactinoStatus == Constant.BUY) {

                UserPosition userPositionTemplete = new UserPosition();
                userPositionTemplete.setUserId(userId);
                userPositionTemplete.setStockId(stockId);
                userPositionTemplete.setVolume(userPosition.getVolume()+volume);
                userPositionTemplete.setFirstStatus(Constant.NO_FIRST_BUY);

                //PrincipalInput only will be changed when buy stock
                userPositionTemplete.setPrincipalInput(userPosition.getPrincipalInput().add(totalTransactionPrice));
                userPositionMapper.updateUserPositionByUserPosition(userPositionTemplete);
            }

        }
        else { //This status only appears when buying, so add userPosition

                UserPosition userPositionTemplete = new UserPosition();
                userPositionTemplete.setUserId(userId);
                userPositionTemplete.setStockId(stockId);
                userPositionTemplete.setVolume(volume);
                userPositionTemplete.setFirstStatus(Constant.FIRST_BUY);

                //PrincipalInput only will be changed when buy stock
                userPositionTemplete.setPrincipalInput(totalTransactionPrice);
                userPositionMapper.insertUserPosition(userPositionTemplete);
        }
    }


    @Transactional
    public int stockTrading(StockTrainsaction stockTrainsaction) {

        //get realTimeStock
        RealTimeStock realTimeStock = realTimeStockMapper.selectRealTimeStockByStockId(stockTrainsaction.getStockId());

        //get uer
        User user = userMapper.selectUserByUserId(stockTrainsaction.getUserId());

        //get userPostiion
        UserPosition userPosition = userPositionMapper.selectUserPositionByUserIdAndStockId(stockTrainsaction.getUserId(),stockTrainsaction.getStockId());

        //get total trainsaction price
        BigDecimal totalTransactionPrice = realTimeStock.getCurrentPrice().multiply(BigDecimal.valueOf(stockTrainsaction.getVolume()));

        //Judge whether normal transactions can be carried out.
        int justRes = justTransaction(
                stockTrainsaction.getTrainsactionStatus(),
                totalTransactionPrice,
                stockTrainsaction.getVolume(),
                user.getPrincipalHoldings(),
                userPosition
                );

        if(justRes != Constant.SUCCESS) {
            return justRes;
        }

        //create stock trainsaction
        stockTrainsactionMapper.insertStockTrainsaction(stockTrainsaction);


        //Modify User Principal
        modifyUserPrincipal(
               stockTrainsaction.getTrainsactionStatus(),
                user,
                totalTransactionPrice);

        //Modify UserPostion
        modifyUserPosition(
                stockTrainsaction,
                userPosition,
                totalTransactionPrice);

        return Constant.SUCCESS;

    }

    public StockTrainsaction findLastStockTrainsactionByUserIdAndStockId(int userId, int stockId) {
        List<StockTrainsaction> stockTrainsactionList = stockTrainsactionMapper.selectStockTrainsactionByUserIdAndStockId(userId, stockId);
        if (stockTrainsactionList.size()>0)
            return  stockTrainsactionList.get(0);
        else
            return null;
    }

}
