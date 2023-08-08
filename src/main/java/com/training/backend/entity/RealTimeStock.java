package com.training.backend.entity;

import lombok.Data;

@Data
public class RealTimeStock {
	int stockId;
	String stockName;
	double currentPrice;
	int stockMargin;
	double fluctuation;
	

}
