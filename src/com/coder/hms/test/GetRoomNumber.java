/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.test;

import com.coder.hms.utils.RoomNumberMaker;

public class GetRoomNumber {

	public static void main(String[] args) {
		
		RoomNumberMaker gt = new RoomNumberMaker();
		
		String[] rn = gt.getRoomNumbers();
		
		for(String str : rn) {
			System.out.println(str);
		}
	}
}
