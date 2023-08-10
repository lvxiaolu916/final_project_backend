package com.training.backend.service;

import com.training.backend.dao.StockTrainsactionMapper;
import com.training.backend.dao.UserMapper;
import com.training.backend.dao.UserPositionMapper;
import com.training.backend.entity.StockTrainsaction;
import com.training.backend.entity.User;
import com.training.backend.entity.UserPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPositionMapper userPositionMapper;
    @Autowired
    private StockTrainsactionMapper stockTrainsactionMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public BigDecimal findUserPrincipalHoldingsByUserId(int userId){
        User user = userMapper.selectUserByUserId(userId);
        if(user!=null){
            return user.getPrincipalHoldings();
        }else{
            logger.error("user is null");
            throw new IllegalArgumentException("userId not found: "+userId);
        }

    }

    public List<UserPosition> findUserPositionByUserId(int userId){
        List<UserPosition> result = userPositionMapper.selectUserPositionListByUserId(userId);
        if(result!=null&&result.size()!=0){
            return result;
        }else{
            logger.error("result is null");
            throw new IllegalArgumentException("userId not found: "+userId);
        }
    }

    public UserPosition findUserPositionByUserIdAndStockId(int userId,int stockId){
        UserPosition userPosition = userPositionMapper.selectUserPositionByUserIdAndStockId(userId,stockId);
        if(userPosition!=null){
            return userPosition;
        }else{
            logger.error("userPosition is null");
            throw new IllegalArgumentException("userId and stockId not found: "+userId+" "+stockId);
        }
    }


    public List<StockTrainsaction> findStockTrainsactionByUserId(int userId){
        List<StockTrainsaction> result = stockTrainsactionMapper.selectStockTrainsactionByUserId(userId);
        if(result!=null){
            return result;
        }else{
            logger.error("user is null");
            throw new IllegalArgumentException("userId not found: "+userId);
        }
    }


    public int setPrincipalHoldingsByUserId(int userId,BigDecimal principalHoldings){
        if(userMapper.selectUserByUserId(userId)!=null){
            return userMapper.updatePrincipalHoldingsByUserId(userId,principalHoldings);
        }else{
            logger.error("user is null");
            throw new IllegalArgumentException("userId not found: "+userId);
        }
    }

    public int addUser(User user){
        return userMapper.insertUser(user);
    }

}
