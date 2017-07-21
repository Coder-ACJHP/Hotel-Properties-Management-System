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
	private String type;
	
	@Column(name="price")
	private String price;
	
	@Column(name="status")
	private String status;
	
	@Column(name="personCount")
	private int personCount;
	
	@Column(name="customerGrupName")
	private String customerGrupName;
	
	@Column(name="ReservationId")
	private long ReservationId;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public String getCustomerGrupName() {
		return customerGrupName;
	}

	public void setCustomerGrupName(String customerGrupName) {
		this.customerGrupName = customerGrupName;
	}

	public long getReservationId() {
		return ReservationId;
	}

	public void setReservationId(long reservationId) {
		ReservationId = reservationId;
	}

	public Room(long theRoomId, String number, String type, String price, String status, int personCount,
			String customerGrupName, long reservationId) {
		super();
		this.theRoomId = theRoomId;
		this.number = number;
		this.type = type;
		this.price = price;
		this.status = status;
		this.personCount = personCount;
		this.customerGrupName = customerGrupName;
		ReservationId = reservationId;
	}

	@Override
	public String toString() {
		return "Room [theRoomId=" + theRoomId + ", number=" + number + ", type=" + type + ", price=" + price
				+ ", status=" + status + ", personCount=" + personCount + ", customerGrupName=" + customerGrupName
				+ ", ReservationId=" + ReservationId + "]";
	}

	
	
}
