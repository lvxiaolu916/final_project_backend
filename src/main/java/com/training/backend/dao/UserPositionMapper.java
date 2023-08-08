package com.training.backend.dao;

import com.training.backend.entity.UserPosition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserPositionMapper {
    public int updateUserPosition(UserPosition userPosition);

    public UserPosition findVolumeAndPrincipalInput(@Param("userId")int userId, @Param("stockId")int stockId);

    public int deleteUserPosition(@Param("userId")int userId,@Param("stockId")int stockId);

    public int insertUserPosition(UserPosition userPosition);

    public List<UserPosition> findUserPositionByUserId(int userId);
}
