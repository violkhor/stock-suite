package com.stock.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.stock.model.CompanyModel;
import com.stock.model.HistoricalRecord;
import com.stock.model.Orders;
import com.stock.model.StockInfoModel;

@Repository
public interface StockDao {
    public void insert(List<CompanyModel> cm);

    public void insertHistoricalRecord(final String symbol, List<HistoricalRecord> hr);

    public void updateCompany(List<CompanyModel> cm);

    public List<CompanyModel> findAllCompanies();

    public List<CompanyModel> findCompanyByExchange(String exchange);

    public void insertRecentStockInfo(List<StockInfoModel> stockModel);

    public List<StockInfoModel> getAllRecentStockInfo();

    public void insertOrderDetails(List<Orders> order);

    public Date getLastUpdateTime();
}
