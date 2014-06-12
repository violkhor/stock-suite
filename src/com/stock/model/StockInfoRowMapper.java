package com.stock.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StockInfoRowMapper implements RowMapper<StockInfoModel> {

	@Override
	public StockInfoModel mapRow(ResultSet rs, int i) throws SQLException {
		StockInfoModel model = new StockInfoModel();
		model.setAsk(rs.getString("ask"));
		model.setBid(rs.getString("bid"));
		model.setChangeFromYearLowPercent(rs
				.getString("changeFromYearLowPercent"));
		model.setSymbol(rs.getString("symbol"));
		return model;
	}

}
