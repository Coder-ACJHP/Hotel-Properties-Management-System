package com.coder.hms.daoImpl;


import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.RoomDAO;
import com.coder.hms.entities.Room;

public class RoomDaoImpl implements RoomDAO {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	private final Logger LOGGER = Logger.getLogger(HotelDaoImpl.class.getName());
	
	public RoomDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
		session = dataSourceFactory.getSession();

	}
	
	@Override
	public Room getRoomByRoomNumber(int roomNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRoom(Room room) {
		
		session.saveOrUpdate(room);
		session.getTransaction().commit();
		
		LOGGER.info("Room : " + room + " saved successfully.");
	}

	public List<Room> getAllRooms() {
		Query<Room> query = session.createQuery("from Room", Room.class);
		List<Room> roomList = query.getResultList();
		return roomList;
	}

}
