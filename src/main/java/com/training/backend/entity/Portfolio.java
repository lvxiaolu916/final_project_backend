package com.training.backend.entity;

import lombok.Data;

import java.math.BigDecimal;


public class Portfolio {
    private int stockId;
    private String stockName;
    private BigDecimal currentPrice;
    private BigDecimal returnRates;
    private BigDecimal profits;

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getReturnRates() {
        return returnRates;
    }

    public void setReturnRates(BigDecimal returnRates) {
        this.returnRates = returnRates;
    }

    public BigDecimal getProfits() {
        return profits;
    }

    public void setProfits(BigDecimal profits) {
        this.profits = profits;
    }
}
