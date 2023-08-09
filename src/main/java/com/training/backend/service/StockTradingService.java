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
import java.util.Date;
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
    private int justTransaction(int trainsactionStatus, double totalTransactionPrice, int volume, double userPrincipal, UserPosition userPosition) {
        if (trainsactionStatus == Constant.BUY && totalTransactionPrice > userPrincipal)
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


    private void modifyUserPrincipal(int trainsactionStatus, User user, double totalTransactionPrice) {
        double currentUserPrincipal = 0;

        if (trainsactionStatus == Constant.BUY) {
            currentUserPrincipal = user.getPrincipalHoldings()-totalTransactionPrice;
        }
        else {
            currentUserPrincipal = user.getPrincipalHoldings()+totalTransactionPrice;
        }
        userMapper.updatePrincipalHoldingsByUserId(user.getUserId(),currentUserPrincipal);
    }

    private void modifyUserPosition (StockTrainsaction stockTrainsaction, UserPosition userPosition,  double totalTransactionPrice) {

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

                double rate = (double)(userPosition.getVolume()-volume)/userPosition.getVolume();
                logger.info("rate is :"+String.valueOf(rate));

                userPositionTemplete.setPrincipalInput(userPosition.getPrincipalInput()*rate);

                userPositionMapper.updateUserPositionByUserPosition(userPositionTemplete);
            }
            else if(trainsactinoStatus == Constant.BUY) {

                UserPosition userPositionTemplete = new UserPosition();
                userPositionTemplete.setUserId(userId);
                userPositionTemplete.setStockId(stockId);
                userPositionTemplete.setVolume(userPosition.getVolume()+volume);

                //PrincipalInput only will be changed when buy stock
                userPositionTemplete.setPrincipalInput(userPosition.getPrincipalInput()+totalTransactionPrice);
                userPositionMapper.updateUserPositionByUserPosition(userPositionTemplete);
            }

        }
        else { //This status only appears when buying, so add userPosition

                UserPosition userPositionTemplete = new UserPosition();
                userPositionTemplete.setUserId(userId);
                userPositionTemplete.setStockId(stockId);
                userPositionTemplete.setVolume(volume);

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
        double totalTransactionPrice = realTimeStock.getCurrentPrice()*stockTrainsaction.getVolume();

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

}
