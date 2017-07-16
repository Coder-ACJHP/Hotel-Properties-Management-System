package com.coder.hms.dao;

import com.coder.hms.entities.Hotel;

public interface HotelDAO {

	public void saveHotel(Hotel hotel);
	
	public Hotel getHotel();
	
}
