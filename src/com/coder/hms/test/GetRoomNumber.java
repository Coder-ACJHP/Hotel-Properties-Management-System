package com.coder.hms.test;

import com.coder.hms.utils.SetRoomNumbers;

public class GetRoomNumber {

	public static void main(String[] args) {
		
		SetRoomNumbers gt = new SetRoomNumbers();
		
		String[] rn = gt.getRoomNumbers();
		
		for(String str : rn) {
			System.out.println(str);
		}
	}
}
