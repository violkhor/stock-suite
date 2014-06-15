package com.stock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stock.dao.StockDao;
import com.stock.model.CompanyModel;
import com.stock.model.HistoricalRecord;
import com.stock.model.StockInfoModel;

@Service("stockManager")
public class StockManagerImpl implements StockManager {
    @Autowired
    @Qualifier("stockDao")
    private StockDao stockDao;

    public void insertCompanies(List<CompanyModel> cm) {
        stockDao.insert(cm);
    }

    public void insertHistoricalRecords(String symbol, List<HistoricalRecord> hr) {
        stockDao.insertHistoricalRecord(symbol, hr);
    }

    public List<CompanyModel> findAllCompanies() {
        return stockDao.findAllCompanies();
    }

    public void updateExchange(List<CompanyModel> cm) {
        stockDao.updateCompany(cm);
    }

    public List<CompanyModel> findCompanyByExchange(String exchange) {
        return stockDao.findCompanyByExchange(exchange);
    }

    public void insertRecentStockInfo(List<StockInfoModel> stockModel) {
        stockDao.insertRecentStockInfo(stockModel);
    }

    @Override
    public List<StockInfoModel> getAllRecentStockInfo() {
        return stockDao.getAllRecentStockInfo();
    }

    @Override
    public Date getLastUpdateTime() {
        return stockDao.getLastUpdateTime();
    }
}
