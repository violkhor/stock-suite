package com.stock.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class Orders {

	private Date orderDate;
	private String symbol;
	private String desc;
	private String orderAction;
	private Integer quantity;
	private BigDecimal price;
	private BigDecimal amount;

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public CurrencyEnum getCurr() {
		return curr;
	}

	public void setCurr(CurrencyEnum curr) {
		this.curr = curr;
	}

	public String getOrderAction() {
		return orderAction;
	}

	public void setOrderAction(String orderAction) {
		this.orderAction = orderAction;
	}

	private CurrencyEnum curr;
}
