package com.training.backend.dao;

import com.training.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface UserMapper {

    int updatePrincipalHoldingsByUserId(int userId, BigDecimal principalHoldings);

    User selectUserByUserId(int userId);

    int insertUser(User user);
}
