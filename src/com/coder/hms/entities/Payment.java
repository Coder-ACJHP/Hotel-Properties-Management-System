package com.coder.hms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Payment")
public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="paymentType")
	private String paymentType;
	
	@Column(name="price")
	private String price;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="explanation")
	private String explanation;
	
	@Column(name="roomNumber")
	private String roomNumber;
	
	@Column(name="dateTime")
	private String dateTime;
	
	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}
	
	public void setId(long Id) {
		this.id = Id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Object getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String theExplanation) {
		this.explanation = theExplanation;
	}
	
	public String getRoomNumber() {
		return this.roomNumber;
	}
	
	public void setRoomNumber(String theRoomNumber) {
		this.roomNumber = theRoomNumber;
	}
	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Payment(long Id, String title, String paymentType, String price, String currency, String explanation,
			String theRoomNumber, String dateTime) {
		super();
		this.id = Id;
		this.title = title;
		this.paymentType = paymentType;
		this.price = price;
		this.currency = currency;
		this.explanation = explanation;
		this.roomNumber = theRoomNumber;
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Payment [id="+ id +", title=" + title + ", paymentType=" + paymentType + ", price=" + price + ", currency="
				+ currency + ", explanation=" + explanation + ", roomNumber=" + roomNumber + ", dateTime=" + dateTime + "]";
	}

}
