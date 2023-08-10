package com.training.backend.entity;

import lombok.Data;

import java.math.BigDecimal;


public class Market {
    private int stockId;
    private String stockName;
    private BigDecimal currentPrice;
    private BigDecimal priceFluctuation;
    private BigDecimal fluctuationRate;

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

    public BigDecimal getPriceFluctuation() {
        return priceFluctuation;
    }

    public void setPriceFluctuation(BigDecimal priceFluctuation) {
        this.priceFluctuation = priceFluctuation;
    }

    public BigDecimal getFluctuationRate() {
        return fluctuationRate;
    }

    public void setFluctuationRate(BigDecimal fluctuationRate) {
        this.fluctuationRate = fluctuationRate;
    }
}
