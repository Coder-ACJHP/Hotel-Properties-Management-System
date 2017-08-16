package com.coder.hms.beans;

public class RoomInfoRow {
	
	private Object roomNumber;
	private Object roomType;
	private Object customerName;
	private Object customerSurname;
	
	public RoomInfoRow() {
		
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
	public Object getCustomerName() {
		return customerName;
	}
	public void setCustomerName(Object customerName) {
		this.customerName = customerName;
	}
	public Object getCustomerSurname() {
		return customerSurname;
	}
	public void setCustomerSurname(Object customerSurname) {
		this.customerSurname = customerSurname;
	}

	public RoomInfoRow(Object roomNumber, Object roomType, Object customerName, Object customerSurname) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.customerName = customerName;
		this.customerSurname = customerSurname;
	}
	@Override
	public String toString() {
		return "RoomInfoRow [roomNumber=" + roomNumber + ", roomType=" + roomType + ", customerName=" + customerName
				+ ", customerSurname=" + customerSurname +"]";
	}
	
}
