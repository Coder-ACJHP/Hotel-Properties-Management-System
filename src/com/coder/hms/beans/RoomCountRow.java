/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.beans;

import java.io.Serializable;

public class RoomCountRow implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object roomNumber;
	private Object roomType;
	private Object personCount;
	private Object price;
	private Object currency;

	public RoomCountRow() {
		// TODO Auto-generated constructor stub
	}

	public Object getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Object roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Object getRoomType() {
		return roomType;
	}

	public void setRoomType(Object roomType) {
		this.roomType = roomType;
	}

	public Object getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Object personCount) {
		this.personCount = personCount;
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

	public RoomCountRow(Object roomNumber, Object roomType, Object personCount, Object price, Object currency) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.personCount = personCount;
		this.price = price;
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "RoomCountRow [roomNumber=" + roomNumber + ", roomType=" + roomType + ", personCount=" + personCount
				+ ", price=" + price + ", currency=" + currency + "]";
	}

}
