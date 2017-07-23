/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.dao;

import com.coder.hms.entities.Room;

public interface RoomDAO {

	public Room getRoomByRoomNumber(int roomNumber);
	
	public void saveRoom(Room room);
}
