package com.training.backend.dao;

import com.training.backend.entity.StockTrainsaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockTrainsactionMapper {

    int insertStockTrainsaction(StockTrainsaction stockTrainsaction);

    List<StockTrainsaction> selectStockTrainsactionByUserId(int userId);
}
