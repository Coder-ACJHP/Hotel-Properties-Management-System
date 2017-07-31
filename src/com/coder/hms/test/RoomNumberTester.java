package com.coder.hms.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.coder.hms.utils.RoomNumberMaker;

public class RoomNumberTester {

	public static void main(String[] args) {
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		RoomNumberMaker rMaker = new RoomNumberMaker();
		
		System.out.println(rMaker.getNotReservedRooms(sdf.format(new Date())));
			
	}
}
