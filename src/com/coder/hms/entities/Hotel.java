/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
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
	
    @Column(name="starCount")
	private int starCount;
    
    @Column(name="picture")
    private String picture;
    
	@Column(name="Owner")
	private String Owner;	
	
	@Column(name="PhoneNumber")
	private String PhoneNumber;
	
	@Column(name="country")
    private String country;
	
	@Column(name="type")
    private String type;
	
	@Column(name="RoomCapacity")
	private int RoomCapacity;
	
	@Column(name="city")
    private String city;
	
	@Column(name="RoomTypes")
	private String RoomTypes;

	@Column(name="Address")
	private String Address;
	
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

	public int getStarCount() {
		return starCount;
	}

	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRoomCapacity() {
		return RoomCapacity;
	}

	public void setRoomCapacity(int roomCapacity) {
		RoomCapacity = roomCapacity;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRoomTypes() {
		return RoomTypes;
	}

	public void setRoomTypes(String roomTypes) {
		RoomTypes = roomTypes;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Hotel(long id, String name, int starCount, String picture, String owner, String phoneNumber, String country,
			String type, int roomCapacity, String city, String roomTypes, String address) {
		super();
		Id = id;
		Name = name;
		this.starCount = starCount;
		this.picture = picture;
		Owner = owner;
		PhoneNumber = phoneNumber;
		this.country = country;
		this.type = type;
		RoomCapacity = roomCapacity;
		this.city = city;
		RoomTypes = roomTypes;
		Address = address;
	}

	@Override
	public String toString() {
		return "Hotel [Id=" + Id + ", Name=" + Name + ", starCount=" + starCount + ", picture=" + picture + ", Owner="
				+ Owner + ", PhoneNumber=" + PhoneNumber + ", country=" + country + ", type=" + type + ", RoomCapacity="
				+ RoomCapacity + ", city=" + city + ", RoomTypes=" + RoomTypes + ", Address=" + Address + "]";
	}

}