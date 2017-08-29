package com.coder.hms.entities;

public class ReportObject {

	private long Id;
	private String userName;
	private String groupName;
	private String checkoutDate;
	private String checkinDate;
	private String agency;
	private String agencyRefNo;
	private int totalDays;
	private String roomType;
	private String hostType;
	private String theNumber;
	private String type;
	private double price;
	private boolean paymentStatus;
	private String currency;
	private String balance;
	private String paymentType;
	
	public ReportObject() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAgencyRefNo() {
		return agencyRefNo;
	}

	public void setAgencyRefNo(String agencyRefNo) {
		this.agencyRefNo = agencyRefNo;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public String getTheNumber() {
		return theNumber;
	}

	public void setTheNumber(String theNumber) {
		this.theNumber = theNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public ReportObject(long id, String userName, String groupName, String checkoutDate, String checkinDate,
			String agency, String agencyRefNo, int totalDays, String roomType, String hostType, String theNumber,
			String type, double price, boolean paymentStatus, String currency, String balance, String paymentType) {
		super();
		Id = id;
		this.userName = userName;
		this.groupName = groupName;
		this.checkoutDate = checkoutDate;
		this.checkinDate = checkinDate;
		this.agency = agency;
		this.agencyRefNo = agencyRefNo;
		this.totalDays = totalDays;
		this.roomType = roomType;
		this.hostType = hostType;
		this.theNumber = theNumber;
		this.type = type;
		this.price = price;
		this.paymentStatus = paymentStatus;
		this.currency = currency;
		this.balance = balance;
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "ReportObject [Id=" + Id + ", userName=" + userName + ", groupName=" + groupName + ", checkoutDate="
				+ checkoutDate + ", checkinDate=" + checkinDate + ", agency=" + agency + ", agencyRefNo=" + agencyRefNo
				+ ", totalDays=" + totalDays + ", roomType=" + roomType + ", hostType=" + hostType + ", theNumber="
				+ theNumber + ", type=" + type + ", price=" + price + ", paymentStatus=" + paymentStatus + ", currency="
				+ currency + ", balance=" + balance + ", paymentType=" + paymentType + "]";
	}
	
	
}
