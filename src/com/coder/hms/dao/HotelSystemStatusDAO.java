package com.coder.hms.dao;

import org.hibernate.Session;

import com.coder.hms.entities.HotelSystemStatus;

public interface HotelSystemStatusDAO {

	public HotelSystemStatus getSystemStatus();
	
	public void updateSystemStatus(HotelSystemStatus hotelSystemStatus);
	
	public void beginTransactionIfAllowed(Session theSession);
}
