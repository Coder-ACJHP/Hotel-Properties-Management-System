/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Reservation")
public class Reservation implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long Id;

    @Column(name = "groupName")
    private String groupName;

    @Column(name = "checkinDate")
    private String checkinDate;

    @Column(name = "checkoutDate")
    private String checkoutDate;

    @Column(name = "totalDays")
    private int totalDays;

    @Column(name = "agency")
    private String agency;

    @Column(name = "hostType")
    private String hostType;

    @Column(name = "creditType")
    private String creditType;

    @Column(name = "bookStatus")
    private String bookStatus;

    @Column(name = "note")
    private String note;

    @Column(name = "paymentStatus")
    private boolean paymentStatus;

    //we need to hiring room details because when the room
    //putting at default after checkout still we need reservation
    //details and that window included some room details.
    @Column(name = "rentedRoomNum")
    private String rentedRoomNum;

    @Column(name = "rentedRoomType")
    private String rentedRoomType;

    @Column(name = "personCount")
    private String personCount;

    @Column(name = "rentedRoomPrice")
    private String rentedRoomPrice;

    @Column(name = "rentedRoomCurrency")
    private String rentedRoomCurrency;

    @Column(name = "isCheckedIn")
    private String isCheckedIn;

    @Column(name = "agencyRefNo")
    private String agencyRefNo;

    @Column(name = "referanceNo")
    private String referanceNo;

    public Reservation() {
        // TODO Auto-generated constructor stub
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
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

    public String getRentedRoomNum() {
        return rentedRoomNum;
    }

    public void setRentedRoomNum(String rentedRoomNum) {
        this.rentedRoomNum = rentedRoomNum;
    }

    public String getRentedRoomType() {
        return rentedRoomType;
    }

    public void setRentedRoomType(String rentedRoomType) {
        this.rentedRoomType = rentedRoomType;
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }

    public String getRentedRoomPrice() {
        return rentedRoomPrice;
    }

    public void setRentedRoomPrice(String rentedRoomPrice) {
        this.rentedRoomPrice = rentedRoomPrice;
    }

    public String getRentedRoomCurrency() {
        return rentedRoomCurrency;
    }

    public void setRentedRoomCurrency(String rentedRoomCurrency) {
        this.rentedRoomCurrency = rentedRoomCurrency;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.Id ^ (this.Id >>> 32));
        hash = 23 * hash + Objects.hashCode(this.groupName);
        hash = 23 * hash + Objects.hashCode(this.checkinDate);
        hash = 23 * hash + Objects.hashCode(this.checkoutDate);
        hash = 23 * hash + this.totalDays;
        hash = 23 * hash + Objects.hashCode(this.agency);
        hash = 23 * hash + Objects.hashCode(this.hostType);
        hash = 23 * hash + Objects.hashCode(this.creditType);
        hash = 23 * hash + Objects.hashCode(this.bookStatus);
        hash = 23 * hash + Objects.hashCode(this.note);
        hash = 23 * hash + (this.paymentStatus ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.rentedRoomNum);
        hash = 23 * hash + Objects.hashCode(this.rentedRoomType);
        hash = 23 * hash + Objects.hashCode(this.personCount);
        hash = 23 * hash + Objects.hashCode(this.rentedRoomPrice);
        hash = 23 * hash + Objects.hashCode(this.rentedRoomCurrency);
        hash = 23 * hash + Objects.hashCode(this.isCheckedIn);
        hash = 23 * hash + Objects.hashCode(this.agencyRefNo);
        hash = 23 * hash + Objects.hashCode(this.referanceNo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (this.totalDays != other.totalDays) {
            return false;
        }
        if (this.paymentStatus != other.paymentStatus) {
            return false;
        }
        if (!Objects.equals(this.groupName, other.groupName)) {
            return false;
        }
        if (!Objects.equals(this.checkinDate, other.checkinDate)) {
            return false;
        }
        if (!Objects.equals(this.checkoutDate, other.checkoutDate)) {
            return false;
        }
        if (!Objects.equals(this.agency, other.agency)) {
            return false;
        }
        if (!Objects.equals(this.hostType, other.hostType)) {
            return false;
        }
        if (!Objects.equals(this.creditType, other.creditType)) {
            return false;
        }
        if (!Objects.equals(this.bookStatus, other.bookStatus)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.rentedRoomNum, other.rentedRoomNum)) {
            return false;
        }
        if (!Objects.equals(this.rentedRoomType, other.rentedRoomType)) {
            return false;
        }
        if (!Objects.equals(this.personCount, other.personCount)) {
            return false;
        }
        if (!Objects.equals(this.rentedRoomPrice, other.rentedRoomPrice)) {
            return false;
        }
        if (!Objects.equals(this.rentedRoomCurrency, other.rentedRoomCurrency)) {
            return false;
        }
        if (!Objects.equals(this.isCheckedIn, other.isCheckedIn)) {
            return false;
        }
        if (!Objects.equals(this.agencyRefNo, other.agencyRefNo)) {
            return false;
        }
        if (!Objects.equals(this.referanceNo, other.referanceNo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "Id=" + Id + ", groupName=" + groupName + ", checkinDate=" + checkinDate + ", checkoutDate=" + checkoutDate + ", totalDays=" + totalDays + ", agency=" + agency + ", hostType=" + hostType + ", creditType=" + creditType + ", bookStatus=" + bookStatus + ", note=" + note + ", paymentStatus=" + paymentStatus + ", rentedRoomNum=" + rentedRoomNum + ", rentedRoomType=" + rentedRoomType + ", personCount=" + personCount + ", rentedRoomPrice=" + rentedRoomPrice + ", rentedRoomCurrency=" + rentedRoomCurrency + ", isCheckedIn=" + isCheckedIn + ", agencyRefNo=" + agencyRefNo + ", referanceNo=" + referanceNo + '}';
    }

    public Reservation(long Id, String groupName, String checkinDate, String checkoutDate, int totalDays, String agency, String hostType, String creditType, String bookStatus, String note, boolean paymentStatus, String rentedRoomNum, String rentedRoomType, String personCount, String rentedRoomPrice, String rentedRoomCurrency, String isCheckedIn, String agencyRefNo, String referanceNo) {
        this.Id = Id;
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
        this.rentedRoomNum = rentedRoomNum;
        this.rentedRoomType = rentedRoomType;
        this.personCount = personCount;
        this.rentedRoomPrice = rentedRoomPrice;
        this.rentedRoomCurrency = rentedRoomCurrency;
        this.isCheckedIn = isCheckedIn;
        this.agencyRefNo = agencyRefNo;
        this.referanceNo = referanceNo;
    }

}
