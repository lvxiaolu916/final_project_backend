package com.training.backend.dao;

import com.training.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int updatePrincipalHoldingsByUserId(int userId, int principalHoldings);

    User selectUserByUserId(int userId);

    int insertUser(User user);
}
