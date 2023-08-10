package com.training.backend.entity;

import java.math.BigDecimal;


public class RealTimeStock {

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

	public int getStockMargin() {
		return stockMargin;
	}

	public void setStockMargin(int stockMargin) {
		this.stockMargin = stockMargin;
	}

	public BigDecimal getFluctuation() {
		return fluctuation;
	}

	public void setFluctuation(BigDecimal fluctuation) {
		this.fluctuation = fluctuation;
	}

	int stockId;
	String stockName;
	BigDecimal currentPrice;
	int stockMargin;
	BigDecimal fluctuation;
	

}
