package com.stock.model;

import java.util.Date;

public class HistoricalRecord {

	public Date tradeDate;
	public double openingPrice;
	public double closingPrice;
	public double dayHigh;
	public double dayLow;
	public double volume;
	public double adjustedClose;

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public double getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(double openingPrice) {
		this.openingPrice = openingPrice;
	}

	public double getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(double closingPrice) {
		this.closingPrice = closingPrice;
	}

	public double getDayHigh() {
		return dayHigh;
	}

	public void setDayHigh(double dayHigh) {
		this.dayHigh = dayHigh;
	}

	public double getDayLow() {
		return dayLow;
	}

	public void setDayLow(double dayLow) {
		this.dayLow = dayLow;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getAdjustedClose() {
		return adjustedClose;
	}

	public void setAdjustedClose(double adjustedClose) {
		this.adjustedClose = adjustedClose;
	}

	@Override
	public String toString() {
		return "Date: " + tradeDate + "\nOpen: " + openingPrice + "\nClose: "
				+ closingPrice + "\nHigh: " + dayHigh + "\nLow: " + dayLow
				+ "\nVolume: " + volume + "\nAdjusted Close: " + adjustedClose;
	}

}
