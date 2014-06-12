package com.stock.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CompanyModelRowMapper implements RowMapper<CompanyModel> {

	public CompanyModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		CompanyModel cm = new CompanyModel();
		cm.setName(rs.getString("companyName"));
		cm.setSymbol(rs.getString("companySymbol"));
		cm.setExchange(rs.getString("exchange"));
		return cm;
	}
}
