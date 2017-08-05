/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
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
	
	@Column(name="groupName")
	private String groupName;
	
	@Column(name="checkinDate")
	private String checkinDate;
	
	@Column(name="checkoutDate")
	private String checkoutDate;
	
	@Column(name="totalDays")
	private int totalDays;
	
	@Column(name="agency")
	private String agency;
	
	@Column(name="hostType")
	private String hostType;
	
	@Column(name="creditType")
	private String creditType;
	
	@Column(name="bookStatus")
	private String bookStatus;
	
	@Column(name="note")
	private String note;
	
	@Column(name="paymentStatus")
	private boolean paymentStatus;
	
	//room id for hiring new room
	@Column(name="theNumber")
	private String theNumber;
	
	@Column(name="isCheckedIn")
	private String isCheckedIn;
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getTheNumber() {
		return theNumber;
	}

	public void setTheNumber(String theNumber) {
		this.theNumber = theNumber;
	}

	public String getIsCheckedIn() {
		return isCheckedIn;
	}

	public void setIsCheckedIn(String isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

	public Reservation(String groupName, String checkinDate, String checkoutDate, int totalDays, String agency,
			String hostType, String creditType, String bookStatus, String note, boolean paymentStatus,
			String theNumber, String isCheckedIn) {
		super();
		this.groupName = groupName;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.totalDays = totalDays;
		this.agency = agency;
		this.hostType = hostType;
		this.creditType = creditType;
		this.bookStatus = bookStatus;
		this.note = note;
		this.paymentStatus = paymentStatus;
		this.theNumber = theNumber;
		this.isCheckedIn = isCheckedIn;
	}

	@Override
	public String toString() {
		return "Reservation [Id=" + Id + ", groupName=" + groupName + ", checkinDate=" + checkinDate + ", checkoutDate="
				+ checkoutDate + ", totalDays=" + totalDays + ", agency=" + agency + ", hostType=" + hostType
				+ ", creditType=" + creditType + ", bookStatus=" + bookStatus + ", note=" + note + ", paymentStatus="
				+ paymentStatus + ", theNumber=" + theNumber + ", isCheckedIn=" + isCheckedIn +"]";
	}

	
}