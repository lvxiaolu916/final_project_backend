package com.training.backend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class StockTrainsaction {
    int stockId;
    int userId;
    int volume;
    int trainsactionStatus;
    Date createTime;
}
