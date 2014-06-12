package com.stock.algo;

import org.springframework.stereotype.Service;

import com.stock.model.StockInfoModel;

@Service
public interface StockAlgoService {

	public StockInfoModel getYearLowStock(StockInfoModel stock);

	public String getString();
}
