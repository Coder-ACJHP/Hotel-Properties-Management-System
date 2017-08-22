/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.coder.hms.daoImpl.HotelDaoImpl;
import com.coder.hms.daoImpl.ReservationDaoImpl;
import com.coder.hms.daoImpl.RoomDaoImpl;
import com.coder.hms.entities.Hotel;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;

public class RoomNumberMaker {

	int counter = 100;
	int lastNum = 0;
	private String roomText;
	private String[] roomNumbers;
	
	public RoomNumberMaker() {
		
		final HotelDaoImpl hotelDaoImpl = new HotelDaoImpl();
		final Hotel hotel = hotelDaoImpl.getHotel();
		
		
		roomNumbers = new String[hotel.getRoomCapacity()];
		for(int i = 0; i < hotel.getRoomCapacity(); i++) {
			lastNum++;
			
			roomText = counter + "" + lastNum;
			
			if (i !=0 && i % 6 == 0) {
				counter += 100;
				lastNum = 0;
			}
			
			roomNumbers[i] = roomText;
		}
	}
	
	public String[] getRoomNumbers() {
		return roomNumbers;
	}
	
	public Object[] getNotReservedRooms(final String date) {
		
		//get All reserved room numbers
		final ReservationDaoImpl rImpl = new ReservationDaoImpl();
		List<Reservation> reservList = rImpl.getReservListByThisDate(date);
		
		//create a String array and add all numbers to in with loop
		final String[] roomNumbers = new String[reservList.size()];
		for(int index=0; index < reservList.size(); index++) {
			
			roomNumbers[index] = reservList.get(index).getTheNumber();
		}
		
		//get all rooms and check them if it status is 'FULL' add to list
		//than remove all list from all rooms.
		final RoomDaoImpl roomDaoImpl = new RoomDaoImpl();
		final List<Room> roomList = roomDaoImpl.getAllRooms();
		
		String[] appendBusys = new String[roomList.size()];
		for (int i=0; i < roomList.size(); i++) {
			if(roomList.get(i).getUsageStatus().equals("FULL")) {
				appendBusys[i] = roomList.get(i).getNumber();
			}
		}
		
		final Collection<String> allRooms = new ArrayList<String>(Arrays.asList(getRoomNumbers()));
		final Collection<String> busyRooms = new ArrayList<String>(Arrays.asList(appendBusys));
		final Collection<String> blockedRooms = new ArrayList<String>(Arrays.asList(roomNumbers));
		allRooms.removeAll(blockedRooms);
		allRooms.removeAll(busyRooms);
		
		return allRooms.toArray();
	}
}
