/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Room;

public interface RoomDAO {

	public Room getRoomByRoomNumber(String roomNumber);
	
	public void saveRoom(Room room);
	
	public List<Room> getAllRooms();
	
	public Room getRoomByReservId(long id);
	
	public void setAllRoomsAtClean(String clean);
	
	public void setSingleRoomAsCleanByRoomNumber(String rowData);
	
	public void setRoomCheckedOut(String num);
	
	public void setAllRoomsAtDirty(String dirty);
	
	public void setSingleRoomAsDirtyByRoomNumber(String roomNumber);
	
	public void setSingleRoomAsDNDByRoomNumber(String roomNumber);
}
