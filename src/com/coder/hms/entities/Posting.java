package com.coder.hms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Posting")
public class Posting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="postType")
	private String postType;
	
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
	
	public Posting() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return Id;
	}

	public void setId(long theId) {
		this.Id = theId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
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

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getRoomNumber() {
		return roomNumber;
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

	public Posting(long theId, String title, String postType, String price, String currency, String explanation,
			String roomNumber, String dateTime) {
		super();
		this.Id = theId;
		this.title = title;
		this.postType = postType;
		this.price = price;
		this.currency = currency;
		this.explanation = explanation;
		this.roomNumber = roomNumber;
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Posting [Id=" + Id + ", title=" + title + ", postType=" + postType + ", price=" + price + ", currency=" + currency
				+ ", explanation=" + explanation + ", roomNumber=" + roomNumber +  ", dateTime=" + dateTime + "]";
	}
}
