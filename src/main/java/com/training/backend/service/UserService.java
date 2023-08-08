package com.training.backend.service;

import com.training.backend.dao.StockTrainsactionMapper;
import com.training.backend.dao.UserMapper;
import com.training.backend.dao.UserPositionMapper;
import com.training.backend.entity.StockTrainsaction;
import com.training.backend.entity.User;
import com.training.backend.entity.UserPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPositionMapper userPositionMapper;
    @Autowired
    private StockTrainsactionMapper stockTrainsactionMapper;

    public double findUserPrincipalHoldingsByUserId(int userId){
        User user = userMapper.selectUserByUserId(userId);
        return user.getPrincipalHoldings();
    }

    public List<UserPosition> findUserPositionByUserId(int userId){
        return userPositionMapper.selectUserPositionListByUserId(userId);
    }

    public List<StockTrainsaction> findStockTrainsactionByUserId(int userId){
        return stockTrainsactionMapper.selectStockTrainsactionByUserId(userId);
    }

    public int setPrincipalHoldingsByUserId(int userId,double principalHoldings){
        return userMapper.updatePrincipalHoldingsByUserId(userId,principalHoldings);
    }

    public int addUser(User user){
        return userMapper.insertUser(user);
    }

}
