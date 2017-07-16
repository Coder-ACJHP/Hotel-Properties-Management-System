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
	@Column(name="RoomNumber")
	private long number;
	
	@Column(name="roomType")
	private RoomType type;
	
	@Column(name="customerId")
	private long customerId;
	
	@Column(name="RoomPrice")
	private double price;
	
	@Column(name="status")
	private RoomStatus status;
	
	public Room() {
		// TODO Auto-generated constructor stub
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Room [number=" + number + ", type=" + type + ", customerId=" + customerId + ", price=" + price
				+ ", status=" + status + "]";
	}

	public Room(long number, RoomType type, long customerId, double price, RoomStatus status) {
		super();
		this.number = number;
		this.type = type;
		this.customerId = customerId;
		this.price = price;
		this.status = status;
	}
	
}
