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
    private int justTransaction(int trainsactionStatus, double totalTransactionPrice, int volume, double userPrincipal, int stockMargin, UserPosition userPosition) {
        if (trainsactionStatus == Constant.BUY && totalTransactionPrice > userPrincipal)
        {
            return Constant.USER_NOT_ENOUGH_PRINCIPAL;
        }
        else if (trainsactionStatus == Constant.BUY && volume > stockMargin)
        {
            return Constant.STOCK_NOT_ENOUGH_VOLUME;
        }
        else if (trainsactionStatus == Constant.SELL && userPosition != null && volume > userPosition.getVolume())
        {
            return Constant.USER_POSITION_NOT_ENOUGH_VOLUEM;
        }
        else if (trainsactionStatus == Constant.SELL && userPosition == null)
        {

            logger.error("USER_POSITION_IS_NULL_WHEN_SELL");
            throw new IllegalArgumentException("USER_POSITION_IS_NULL_WHEN_SELL");
//            return Constant.USER_POSITION_IS_NULL_WHEN_SELL;
        }
        else {
            return Constant.SUCCESS;
        }
    }

    private void modifyRealTimeStockMapper(int trainsacionStatus, RealTimeStock realTimeStock, int volume) {

        if (trainsacionStatus == Constant.BUY) {
            realTimeStockMapper.updateMarginByStockId(realTimeStock.getStockId(),realTimeStock.getStockMargin() - volume);
        }
        else {
            realTimeStockMapper.updateMarginByStockId(realTimeStock.getStockId(), realTimeStock.getStockMargin() + volume);
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

    private void modifyUserPosition (int trainsactinoStatus, int userId, int stockId, UserPosition userPosition, int volume, double totalTransactionPrice) throws IllegalArgumentException{

        if (userPosition != null) {

            if (trainsactinoStatus == Constant.SELL && userPosition.getVolume() == volume) {

                userPositionMapper.deleteUserPositionByUserIdAndStockId(userId, stockId);
            }
            else if(trainsactinoStatus == Constant.SELL && userPosition.getVolume() != volume) {

                UserPosition userPositionTemplete = new UserPosition();
                userPositionTemplete.setUserId(userId);
                userPositionTemplete.setStockId(stockId);
                userPositionTemplete.setVolume(userPosition.getVolume()-volume);

                //PrincipalInput only will be changed when buy stock
                userPositionTemplete.setPrincipalInput(userPosition.getPrincipalInput());

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

    private void createStockTrainsaction(int trainsactionStatus, Date time, int userId, int stockId, int volume) {
        StockTrainsaction stockTrainsaction = new StockTrainsaction();
        stockTrainsaction.setTrainsactionStatus(trainsactionStatus);
        stockTrainsaction.setStockId(stockId);
        stockTrainsaction.setUserId(userId);
        stockTrainsaction.setVolume(volume);
        stockTrainsaction.setCreateTime(time);
        stockTrainsactionMapper.insertStockTrainsaction(stockTrainsaction);
    }

    /*
     * input param: Map<String,Object>
     * Key:stockId,userId,volume,time,trainsactionStatus
     * Value data type:int, int, int, Date, int
     * */
    @Transactional
    public int stockTrading(Map<String,Object> tradingMap) {

        //get realTimeStock
        RealTimeStock realTimeStock = realTimeStockMapper.selectRealTimeStockByStockId((int)tradingMap.get("stockId"));

        //get uer
        User user = userMapper.selectUserByUserId((int)tradingMap.get("userId"));

        //get userPostiion
        UserPosition userPosition = userPositionMapper.selectUserPositionByUserIdAndStockId((int)tradingMap.get("userId"),(int)tradingMap.get("stockId"));

        //get total trainsaction price
        double totalTransactionPrice = realTimeStock.getCurrentPrice()*(int)tradingMap.get("volume");

        //Judge whether normal transactions can be carried out.
        int justRes = justTransaction(
                (int)tradingMap.get("trainsactionStatus"),
                totalTransactionPrice,
                (int)tradingMap.get("volume"),
                user.getPrincipalHoldings(),
                realTimeStock.getStockMargin(),
                userPosition
                );

        if(justRes != Constant.SUCCESS) {
            return justRes;
        }

        //create stock trainsaction
        createStockTrainsaction(
                (int)tradingMap.get("trainsactionStatus"),
                (Date)tradingMap.get("time"),
                user.getUserId(),
                realTimeStock.getStockId(),
                (int)tradingMap.get("volume"));

        //Modify RealTimeStock
        modifyRealTimeStockMapper(
                (int)tradingMap.get("trainsactionStatus"),
                realTimeStock,
                (int)tradingMap.get("volume"));

        //Modify User Principal
        modifyUserPrincipal(
                (int)tradingMap.get("trainsactionStatus"),
                user,
                totalTransactionPrice);

        //Modify UserPostion
        modifyUserPosition(
                (int)tradingMap.get("trainsactionStatus"),
                user.getUserId(),
                realTimeStock.getStockId(),
                userPosition,
                (int)tradingMap.get("volume"),
                totalTransactionPrice);

        return Constant.SUCCESS;

    }

}
