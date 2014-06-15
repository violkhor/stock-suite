package com.stock.service;

import java.util.Date;
import java.util.List;

import com.stock.model.CompanyModel;
import com.stock.model.HistoricalRecord;
import com.stock.model.StockInfoModel;

public interface StockManager {
    public void insertCompanies(List<CompanyModel> cm);

    public void insertHistoricalRecords(String symbol, List<HistoricalRecord> hr);

    public List<CompanyModel> findAllCompanies();

    public void updateExchange(List<CompanyModel> cm);

    public List<CompanyModel> findCompanyByExchange(String exchange);

    public void insertRecentStockInfo(List<StockInfoModel> stockModel);

    public List<StockInfoModel> getAllRecentStockInfo();

    public Date getLastUpdateTime();

    public void truncateRecentStock();
}
