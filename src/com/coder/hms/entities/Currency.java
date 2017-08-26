package com.coder.hms.entities;

import java.io.Serializable;

public class Currency implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object base;
	private Object date;
	private Object rates;
	
	public Currency() {
		// TODO Auto-generated constructor stub
	}

	public Object getBase() {
		return base;
	}

	public void setBase(Object base) {
		this.base = base;
	}

	public Object getDate() {
		return date;
	}

	public void setDate(Object date) {
		this.date = date;
	}

	public Object getRates() {
		return rates;
	}

	public void setRates(Object rates) {
		this.rates = rates;
	}

	public Currency(Object base, Object date, Object rates) {
		super();
		this.base = base;
		this.date = date;
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "Currency [base=" + base + ", date=" + date + ", rates=" + rates + "]";
	}
	
	
}
