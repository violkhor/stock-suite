package com.stock.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.stock.model.Orders;

public interface OrdersFileManager {
	public void insertOrderDetail(List<Orders> orders);

	public List<Orders> readCsv(File file) throws ParseException,
			FileNotFoundException, IOException;
}
