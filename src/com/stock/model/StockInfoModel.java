package com.stock.model;

public class StockInfoModel {

	private String symbol;
	private String ask;
	private String bid;
	private String dividendPerShare;
	private String dividendYield;
	private String earningPerShare;
	private String epsEstimateCurrentYear;
	private String epsEstimateNextYear;
	private String epsEstimateNextQuarter;
	private String dayLow;
	private String dayHigh;
	private String yearLow;
	private String yearHigh;
	private String marketCap;
	private String volume;
	public String lastTradePrice;
	private String changeFromYearLowPercent;
	private String changeFromYearHighPercent;
	private String exchange;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getLastTradePrice() {
		return lastTradePrice;
	}

	public void setLastTradePrice(String lastTradePrice) {
		this.lastTradePrice = lastTradePrice;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String token) {
		this.ask = token;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getDividendPerShare() {
		return dividendPerShare;
	}

	public void setDividendPerShare(String dividendPerShare) {
		this.dividendPerShare = dividendPerShare;
	}

	public String getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(String dividendYield) {
		this.dividendYield = dividendYield;
	}

	public String getEarningPerShare() {
		return earningPerShare;
	}

	public void setEarningPerShare(String earningPerShare) {
		this.earningPerShare = earningPerShare;
	}

	public String getEpsEstimateCurrentYear() {
		return epsEstimateCurrentYear;
	}

	public void setEpsEstimateCurrentYear(String epsEstimateCurrentYear) {
		this.epsEstimateCurrentYear = epsEstimateCurrentYear;
	}

	public String getEpsEstimateNextYear() {
		return epsEstimateNextYear;
	}

	public void setEpsEstimateNextYear(String epsEstimateNextYear) {
		this.epsEstimateNextYear = epsEstimateNextYear;
	}

	public String getEpsEstimateNextQuarter() {
		return epsEstimateNextQuarter;
	}

	public void setEpsEstimateNextQuarter(String epsEstimateNextQuarter) {
		this.epsEstimateNextQuarter = epsEstimateNextQuarter;
	}

	public String getDayLow() {
		return dayLow;
	}

	public void setDayLow(String dayLow) {
		this.dayLow = dayLow;
	}

	public String getDayHigh() {
		return dayHigh;
	}

	public void setDayHigh(String dayHigh) {
		this.dayHigh = dayHigh;
	}

	public String getYearLow() {
		return yearLow;
	}

	public void setYearLow(String yearLow) {
		this.yearLow = yearLow;
	}

	public String getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "Last Trade Price: " + lastTradePrice + "\nAsk: " + ask
				+ "\nBid: " + bid + "\nDividend: " + dividendPerShare
				+ "\nEPS: " + earningPerShare
				+ "\nearningEstimateCurrentYear: " + epsEstimateCurrentYear
				+ "\nearningEstimateNextYear: " + epsEstimateNextYear
				+ "\n52 Week Low: " + yearLow + "\n52 Week High: " + yearHigh
				+ "\nMarket Cap: " + marketCap + "\nVolume: " + volume
				+ "\nDividend Yield: " + dividendYield
				+ "\n% Change from 52 week low: " + changeFromYearLowPercent
				+ "\n% Change from 52 week high: " + changeFromYearHighPercent;
	}

	public String getChangeFromYearLowPercent() {
		return changeFromYearLowPercent;
	}

	public void setChangeFromYearLowPercent(String changeFromYearLowPercent) {
		this.changeFromYearLowPercent = changeFromYearLowPercent;
	}

	public String getChangeFromYearHighPercent() {
		return changeFromYearHighPercent;
	}

	public void setChangeFromYearHighPercent(String changeFromYearHighPercent) {
		this.changeFromYearHighPercent = changeFromYearHighPercent;
	}

}
