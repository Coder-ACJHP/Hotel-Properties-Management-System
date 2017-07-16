package com.coder.hms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Reservation")
public class Reservation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="theRoomId")
	private long theRoomId;
	
	@Column(name="checkinDate")
	private String checkinDate;
	
	@Column(name="checkoutDate")
	private String checkoutDate;
	
	@Column(name="paymentStatus")
	private boolean paymentStatus;
	
	@Column(name="hostType")
	private String hostType;
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getTheRoom() {
		return theRoomId;
	}

	public void setTheRoom(long theRoom) {
		this.theRoomId = theRoom;
	}

	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	@Override
	public String toString() {
		return "Reservation [Id=" + Id + ", theRoom=" + theRoomId + ", checkinDate=" + checkinDate + ", checkoutDate="
				+ checkoutDate + ", paymentStatus=" + paymentStatus + ", hostType=" + hostType + "]";
	}
	
	
}
