package com.stock.algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stock.dao.StockDao;
import com.stock.model.StockInfoModel;

@Service("stockAlgoService")
public class StockAlgoServiceImpl implements StockAlgoService {

	@Autowired
	@Qualifier("stockDao")
	private StockDao stockDao;

	public StockInfoModel getYearLowStock(StockInfoModel stock) {

		String number = stock.getChangeFromYearLowPercent().substring(1,
				stock.getChangeFromYearLowPercent().length() - 1);
		String sign = stock.getChangeFromYearLowPercent().substring(0, 1);

		if (number.equals("N/A"))
			return null;
		if (sign.equals("-"))
			return stock;

		try {
			if (Double.parseDouble(number) < 5
					&& Double.parseDouble(stock.getAsk()) > 1)
				return stock;

		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
		return null;
	}

	public String getString() {
		return "Hello";
	}
}
