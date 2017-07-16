package com.coder.hms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Hotel")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
    @Column(name="Name")
	private String Name;
	
	@Column(name="Owner")
	private String Owner;
	
	@Column(name="Address")
	private String Address;
	
	@Column(name="PhoneNumber")
	private String PhoneNumber;
	
	@Column(name="RoomCapacity")
	private int RoomCapacity;
	
	@Column(name="RoomTypes")
	private String RoomTypes;
	
	public Hotel() {
		
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public int getRoomCapacity() {
		return RoomCapacity;
	}

	public void setRoomCapacity(int roomCapacity) {
		RoomCapacity = roomCapacity;
	}

	public String getRoomType() {
		return RoomTypes;
	}

	public void setRoomType(String roomType) {
		this.RoomTypes = roomType;
	}

	public Hotel(String name, String owner, String address, String phoneNumber, int roomCapacity, String roomType) {
		super();
		Name = name;
		Owner = owner;
		Address = address;
		PhoneNumber = phoneNumber;
		RoomCapacity = roomCapacity;
		this.RoomTypes = roomType;
	}

	public Hotel(long Id, String name, String owner, String address, String phoneNumber, int roomCapacity, String roomType) {
		super();
		this.Id = Id;
		Name = name;
		Owner = owner;
		Address = address;
		PhoneNumber = phoneNumber;
		RoomCapacity = roomCapacity;
		this.RoomTypes = roomType;
	}
	
	@Override
	public String toString() {
		return "Hotel [Id="+ Id +"Name=" + Name + ", Owner=" + Owner + ", Address=" + Address + ", PhoneNumber=" + PhoneNumber
				+ ", RoomCapacity=" + RoomCapacity + ", roomType=" + RoomTypes + "]";
	}
	
}
