package com.training.backend.entity;

import java.math.BigDecimal;

public class SingleStockDetail {

    private int holdingVolume;
    private BigDecimal fluctuationRate;
    private BigDecimal fluctuationPrice;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal currentPrice;
    private BigDecimal currentInterestRate;

    public int getHoldingVolume() {
        return holdingVolume;
    }

    public void setHoldingVolume(int holdingVolume) {
        this.holdingVolume = holdingVolume;
    }

    public BigDecimal getFluctuationRate() {
        return fluctuationRate;
    }

    public void setFluctuationRate(BigDecimal fluctuationRate) {
        this.fluctuationRate = fluctuationRate;
    }

    public BigDecimal getFluctuationPrice() {
        return fluctuationPrice;
    }

    public void setFluctuationPrice(BigDecimal fluctuationPrice) {
        this.fluctuationPrice = fluctuationPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getCurrentInterestRate() {
        return currentInterestRate;
    }

    public void setCurrentInterestRate(BigDecimal currentInterestRate) {
        this.currentInterestRate = currentInterestRate;
    }
}
