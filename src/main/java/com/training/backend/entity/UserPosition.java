package com.training.backend.entity;

import lombok.Data;

import java.math.BigDecimal;


public class UserPosition {
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public BigDecimal getPrincipalInput() {
        return principalInput;
    }

    public void setPrincipalInput(BigDecimal principalInput) {
        this.principalInput = principalInput;
    }

    public int getFirstStatus() {
        return firstStatus;
    }

    public void setFirstStatus(int firstStatus) {
        this.firstStatus = firstStatus;
    }

    private int userId;
    private int stockId;
    private int volume;
    private BigDecimal principalInput;
    private int firstStatus;
}
