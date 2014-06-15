package com.stock.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stock.model.CompanyModel;
import com.stock.model.CompanyModelRowMapper;
import com.stock.model.HistoricalRecord;
import com.stock.model.Orders;
import com.stock.model.StockInfoModel;
import com.stock.model.StockInfoRowMapper;

@Repository("stockDao")
public class StockJdbcDao implements StockDao {
    String INSERT_COMPANY = "INSERT INTO company(companyName, companySymbol) VALUES(?,?)";
    String INSERT_HISTORICAL = "INSERT INTO historicalrecord(symbol, tradeDate, openingPrice, closingPrice, dayHigh, dayLow, volume, adjustedClose) values(?,?,?,?,?,?,?,?)";
    String INSERT_RECENT = "INSERT INTO recentstockinfo (ask, bid, dividendPerShare,dividendYield, earningsPerShare, epsEstimateCurrentYear, epsEstimateNextYear, epsEstimateNextQuarter, dayLow,"
            + "dayHigh, yearLow, yearHigh, marketCap,volume, lastTradePrice, changeFromYearLowPercent, changeFromYearHighPercent, exchange, symbol,dateCreated ) "
            + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW())";
    String UPDATE_COMPANY = "UPDATE company SET EXCHANGE = ? WHERE COMPANYSYMBOL = ? AND COMPANYNAME = ?";
    String SELECT_COMPANY_ALL = "SELECT companyName, companySymbol, exchange FROM company where exchange is null";
    String SELECT_COMPANY_EXCHANGE = "SELECT distinct companyName, companySymbol, exchange FROM company where exchange = ':exchange'";
    String INSERT_ORDERS = "INSERT INTO ORDERS(orderDate, symbol, description, orderAction, quantity, price, amount, currency) VALUES(?,?,?,?,?,?,?,?)";
    String SELECT_LAST_UPDATE_TIME = "select max(dateCreated) from recentstockinfo";
    // String SELECT_STOCK_INFO_ALL =
    // "SELECT ask, bid, dividendPerShare,dividendYield, earningsPerShare, epsEstimateCurrentYear, epsEstimateNextYear, epsEstimateNextQuarter, dayLow,"
    // +
    // "dayHigh, yearLow, yearHigh, marketCap,volume, lastTradePrice, changeFromYearLowPercent, changeFromYearHighPercent, exchange, symbol,dateCreated "
    // + "from recentStockInfo";
    String SELECT_STOCK_INFO_ALL = "SELECT symbol, ask, bid, changeFromYearLowPercent from recentstockinfo where exchange = 'Toronto'";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // DEPRECATED
    public void insert(final List<CompanyModel> companyList) {
        final int batchSize = 500;
        for (int j = 0; j < companyList.size(); j += batchSize) {
            final List<CompanyModel> batchList = companyList.subList(j,
                    j + batchSize > companyList.size() ? companyList.size() : j
                            + batchSize);
            jdbcTemplate.batchUpdate(INSERT_COMPANY, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    CompanyModel cm = batchList.get(i);
                    ps.setString(1, cm.getName());
                    ps.setString(2, cm.getSymbol());
                }

                @Override
                public int getBatchSize() {
                    return batchList.size();
                }
            });
        }
    }

    @Override
    public void insertHistoricalRecord(final String symbol,
            List<HistoricalRecord> historicList) {
        final int batchSize = 500;
        for (int j = 0; j < historicList.size(); j += batchSize) {
            final List<HistoricalRecord> batchList = historicList.subList(j, j
                    + batchSize > historicList.size() ? historicList.size() : j
                    + batchSize);
            jdbcTemplate.batchUpdate(INSERT_HISTORICAL,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i)
                                throws SQLException {
                            HistoricalRecord hr = batchList.get(i);
                            ps.setString(1, symbol);
                            ps.setDate(2, new java.sql.Date(hr.getTradeDate().getTime()));
                            ps.setDouble(3, hr.getOpeningPrice());
                            ps.setDouble(4, hr.getClosingPrice());
                            ps.setDouble(5, hr.getDayHigh());
                            ps.setDouble(6, hr.getDayLow());
                            ps.setDouble(7, hr.getVolume());
                            ps.setDouble(8, hr.getAdjustedClose());
                        }

                        @Override
                        public int getBatchSize() {
                            return batchList.size();
                        }
                    });
        }
    }

    public void updateCompany(List<CompanyModel> companyList) {
        final int batchSize = 500;
        for (int j = 0; j < companyList.size(); j += batchSize) {
            final List<CompanyModel> batchList = companyList.subList(j,
                    j + batchSize > companyList.size() ? companyList.size() : j
                            + batchSize);
            jdbcTemplate.batchUpdate(UPDATE_COMPANY, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    CompanyModel cm = batchList.get(i);
                    ps.setString(1, cm.getExchange());
                    ps.setString(2, cm.getSymbol());
                    ps.setString(3, cm.getName());
                }

                @Override
                public int getBatchSize() {
                    return batchList.size();
                }
            });
        }
    }

    public List<CompanyModel> findAllCompanies() {
        return jdbcTemplate.query(SELECT_COMPANY_ALL, new CompanyModelRowMapper());
    }

    public List<CompanyModel> findCompanyByExchange(String exchange) {
        String sql = SELECT_COMPANY_EXCHANGE.replace(":exchange", exchange);
        return jdbcTemplate.query(sql, new CompanyModelRowMapper());
    }

    public void insertRecentStockInfo(List<StockInfoModel> stockList) {
        final int batchSize = 500;
        for (int j = 0; j < stockList.size(); j += batchSize) {
            final List<StockInfoModel> batchList = stockList.subList(j,
                    j + batchSize > stockList.size() ? stockList.size() : j + batchSize);
            jdbcTemplate.batchUpdate(INSERT_RECENT, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    StockInfoModel model = batchList.get(i);
                    System.out.println("==" + model.getDividendYield() + "==");
                    ps.setString(1, model.getAsk());
                    ps.setString(2, model.getBid());
                    ps.setString(3, model.getDividendPerShare());
                    ps.setString(4, model.getDividendYield());
                    ps.setString(5, model.getEarningPerShare());
                    ps.setString(6, model.getEpsEstimateCurrentYear());
                    ps.setString(7, model.getEpsEstimateNextYear());
                    ps.setString(8, model.getEpsEstimateNextQuarter());
                    ps.setString(9, model.getDayLow());
                    ps.setString(10, model.getDayHigh());
                    ps.setString(11, model.getYearLow());
                    ps.setString(12, model.getYearHigh());
                    ps.setString(13, model.getMarketCap());
                    ps.setString(14, model.getVolume());
                    ps.setString(15, model.getLastTradePrice());
                    ps.setString(16, model.getChangeFromYearLowPercent());
                    ps.setString(17, model.getChangeFromYearHighPercent());
                    ps.setString(18, model.getExchange());
                    ps.setString(19, model.getSymbol());
                }

                @Override
                public int getBatchSize() {
                    return batchList.size();
                }
            });
        }
    }

    @Override
    public List<StockInfoModel> getAllRecentStockInfo() {
        return jdbcTemplate.query(SELECT_STOCK_INFO_ALL, new StockInfoRowMapper());
    }

    @Override
    public void insertOrderDetails(final List<Orders> orders) {
        jdbcTemplate.batchUpdate(INSERT_ORDERS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Orders order = orders.get(i);
                ps.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
                ps.setString(2, order.getSymbol());
                ps.setString(3, order.getDesc());
                ps.setString(4, order.getOrderAction());
                ps.setInt(5, order.getQuantity());
                ps.setBigDecimal(6, order.getPrice());
                ps.setBigDecimal(7, order.getAmount());
                ps.setString(8, order.getCurr().name());
            }

            @Override
            public int getBatchSize() {
                return orders.size();
            }
        });
    }

    @Override
    public Date getLastUpdateTime() {
        return jdbcTemplate.queryForObject(SELECT_LAST_UPDATE_TIME, Date.class);
    }
}
