package com.coder.hms.test;

import java.util.Date;

import com.coder.hms.utils.RoomNumberMaker;

public class RoomNumberTester {

	public static void main(String[] args) {
		
		
		RoomNumberMaker rMaker = new RoomNumberMaker();
		
		System.out.println(rMaker.getNotReservedRooms(new Date()));
			
	}
}
