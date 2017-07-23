/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.utils;

import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.entities.Hotel;

public class SetRoomNumbers {

	int counter = 100;
	int lastNum = 0;
	private String roomText;
	private String[] roomNumbers;
	
	public SetRoomNumbers() {
		
		HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
		Hotel hotel = hotelDaoImpl.getHotel();
		final int capacity = hotel.getRoomCapacity();
		
		System.out.println(capacity);
		
		roomNumbers = new String[hotel.getRoomCapacity()];
		for(int i = 1; i < hotel.getRoomCapacity(); i++) {
			lastNum++;
			
			roomText = counter + "" + lastNum;
			
			if (i % 6 == 0) {
				counter += 100;
				lastNum = 0;
			}
			
			roomNumbers[i] = roomText;
		}
	}
	
	public String[] getRoomNumbers() {
		return roomNumbers;
	}
}
