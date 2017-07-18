package com.coder.hms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Room")
public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="theRoomId")
	private long theRoomId;
	
	@Column(name="number")
	private String number;
	
	@Column(name="type")
	private RoomType type;
	
	@Column(name="price")
	private double price;
	
	@Column(name="status")
	private RoomStatus status;
	
	@Column(name="customerId")
	private long customerId;
	
	public Room() {
		// TODO Auto-generated constructor stub
	}

	public long getTheRoomId() {
		return theRoomId;
	}

	public void setTheRoomId(long theRoomId) {
		this.theRoomId = theRoomId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Room [theRoomId=" + theRoomId + ", number=" + number + ", type=" + type + ", price=" + price
				+ ", status=" + status + ", customerId=" + customerId + "]";
	}

	public Room(long theRoomId, String number, RoomType type, double price, RoomStatus status, long customerId) {
		super();
		this.theRoomId = theRoomId;
		this.number = number;
		this.type = type;
		this.price = price;
		this.status = status;
		this.customerId = customerId;
	}

	
	
}
