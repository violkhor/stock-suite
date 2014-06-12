package com.stock.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;

import com.stock.dao.StockDao;
import com.stock.model.CurrencyEnum;
import com.stock.model.Orders;

@Service
public class OrdersFileManagerImpl implements OrdersFileManager {

	@Autowired
	@Qualifier("stockDao")
	private StockDao stockDao;

	@Override
	public void insertOrderDetail(List<Orders> orders) {
		stockDao.insertOrderDetails(orders);
	}

	@Override
	public List<Orders> readCsv(File uploadFile) throws ParseException,
			IOException {
		CSVReader reader = null;
		List<Orders> orderList = new ArrayList<Orders>();

		reader = new CSVReader(new FileReader(uploadFile));
		// reader = new CSVReader(new FileReader(uploadFile));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			Orders order = new Orders();
			order = mapOrder(nextLine);
			orderList.add(order);

		}

		reader.close();

		return orderList;
	}

	private Orders mapOrder(String[] nextLine) throws ParseException {
		Orders order = new Orders();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		order.setOrderDate(formatter.parse(nextLine[0]));

		if (nextLine[1] != null)
			order.setSymbol(nextLine[1]);
		if (nextLine[2] != null)
			order.setDesc(nextLine[2]);
		if (nextLine[3] != null)
			order.setOrderAction(nextLine[3]);
		if (nextLine[4] != null)
			order.setQuantity(Integer.parseInt(nextLine[4]));
		else
			order.setQuantity(null);
		if (nextLine[5] != null)
			order.setPrice(BigDecimal.valueOf(Double.parseDouble(nextLine[5])));
		else
			order.setPrice(null);
		if (nextLine[6] != null)
			order.setAmount(BigDecimal.valueOf(Double.parseDouble(nextLine[6])));
		else
			order.setAmount(null);
		order.setCurr(CurrencyEnum.valueOf(nextLine[7]));

		return order;
	}
}
