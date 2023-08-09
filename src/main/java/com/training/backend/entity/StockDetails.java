package com.training.backend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class StockDetails {

    int stockId;
    String stockName;
    double price;
    Date time;


}
