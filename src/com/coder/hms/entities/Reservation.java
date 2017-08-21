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
	
	@Column(name="agencyRefNo")
	private String agencyRefNo;
	
	@Column(name="referanceNo")
	private String referanceNo;
	
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

	public String getAgencyRefNo() {
		return agencyRefNo;
	}

	public void setAgencyRefNo(String agencyRefNo) {
		this.agencyRefNo = agencyRefNo;
	}

	public String getReferanceNo() {
		return referanceNo;
	}

	public void setReferanceNo(String referanceNo) {
		this.referanceNo = referanceNo;
	}

	public Reservation(long id, String groupName, String checkinDate, String checkoutDate, int totalDays, String agency,
			String hostType, String creditType, String bookStatus, String note, boolean paymentStatus, String theNumber,
			String isCheckedIn, String agencyRefNo, String referanceNo) {
		super();
		Id = id;
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
		this.agencyRefNo = agencyRefNo;
		this.referanceNo = referanceNo;
	}

	@Override
	public String toString() {
		return "Reservation [Id=" + Id + ", groupName=" + groupName + ", checkinDate=" + checkinDate + ", checkoutDate="
				+ checkoutDate + ", totalDays=" + totalDays + ", agency=" + agency + ", hostType=" + hostType
				+ ", creditType=" + creditType + ", bookStatus=" + bookStatus + ", note=" + note + ", paymentStatus="
				+ paymentStatus + ", theNumber=" + theNumber + ", isCheckedIn=" + isCheckedIn + ", agencyRefNo="
				+ agencyRefNo + ", referanceNo=" + referanceNo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		result = prime * result + ((agency == null) ? 0 : agency.hashCode());
		result = prime * result + ((agencyRefNo == null) ? 0 : agencyRefNo.hashCode());
		result = prime * result + ((bookStatus == null) ? 0 : bookStatus.hashCode());
		result = prime * result + ((checkinDate == null) ? 0 : checkinDate.hashCode());
		result = prime * result + ((checkoutDate == null) ? 0 : checkoutDate.hashCode());
		result = prime * result + ((creditType == null) ? 0 : creditType.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((hostType == null) ? 0 : hostType.hashCode());
		result = prime * result + ((isCheckedIn == null) ? 0 : isCheckedIn.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + (paymentStatus ? 1231 : 1237);
		result = prime * result + ((referanceNo == null) ? 0 : referanceNo.hashCode());
		result = prime * result + ((theNumber == null) ? 0 : theNumber.hashCode());
		result = prime * result + totalDays;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (Id != other.Id)
			return false;
		if (agency == null) {
			if (other.agency != null)
				return false;
		} else if (!agency.equals(other.agency))
			return false;
		if (agencyRefNo == null) {
			if (other.agencyRefNo != null)
				return false;
		} else if (!agencyRefNo.equals(other.agencyRefNo))
			return false;
		if (bookStatus == null) {
			if (other.bookStatus != null)
				return false;
		} else if (!bookStatus.equals(other.bookStatus))
			return false;
		if (checkinDate == null) {
			if (other.checkinDate != null)
				return false;
		} else if (!checkinDate.equals(other.checkinDate))
			return false;
		if (checkoutDate == null) {
			if (other.checkoutDate != null)
				return false;
		} else if (!checkoutDate.equals(other.checkoutDate))
			return false;
		if (creditType == null) {
			if (other.creditType != null)
				return false;
		} else if (!creditType.equals(other.creditType))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (hostType == null) {
			if (other.hostType != null)
				return false;
		} else if (!hostType.equals(other.hostType))
			return false;
		if (isCheckedIn == null) {
			if (other.isCheckedIn != null)
				return false;
		} else if (!isCheckedIn.equals(other.isCheckedIn))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (paymentStatus != other.paymentStatus)
			return false;
		if (referanceNo == null) {
			if (other.referanceNo != null)
				return false;
		} else if (!referanceNo.equals(other.referanceNo))
			return false;
		if (theNumber == null) {
			if (other.theNumber != null)
				return false;
		} else if (!theNumber.equals(other.theNumber))
			return false;
		if (totalDays != other.totalDays)
			return false;
		return true;
	}

	
}