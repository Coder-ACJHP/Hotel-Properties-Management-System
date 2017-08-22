package com.coder.hms.beans;

import java.io.Serializable;

public class RoomEarlyPaymentRow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object title;
	private Object type;
	private Object price;
	private Object currency;
	private Object explanation;
	
	public RoomEarlyPaymentRow() {
		// TODO Auto-generated constructor stub
	}

	public Object getTitle() {
		return title;
	}

	public void setTitle(Object title) {
		this.title = title;
	}

	public Object getType() {
		return type;
	}

	public void setType(Object type) {
		this.type = type;
	}

	public Object getPrice() {
		return price;
	}

	public void setPrice(Object price) {
		this.price = price;
	}

	public Object getCurrency() {
		return currency;
	}

	public void setCurrency(Object currency) {
		this.currency = currency;
	}

	public Object getExplanation() {
		return explanation;
	}

	public void setExplanation(Object explanation) {
		this.explanation = explanation;
	}

	public RoomEarlyPaymentRow(Object title, Object type, Object price, Object currency, Object explanation) {
		super();
		this.title = title;
		this.type = type;
		this.price = price;
		this.currency = currency;
		this.explanation = explanation;
	}

	@Override
	public String toString() {
		return "RoomEarlyPaymentRow [title=" + title + ", type=" + type + ", price=" + price + ", currency=" + currency
				+ ", explanation=" + explanation + "]";
	}	

}
