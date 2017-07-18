package com.coder.hms.daoImpl;


import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.RoomDAO;
import com.coder.hms.entities.Room;

public class RoomDaoImpl implements RoomDAO {

	private Session session;
	private Transaction transaction;
	private DataSourceFactory dataSourceFactory;
	private final Logger LOGGER = Logger.getLogger(HotelDaoImpl.class.getName());
	
	public RoomDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		session = dataSourceFactory.getSession();
		transaction = session.beginTransaction();
	}
	
	@Override
	public Room getRoomByRoomNumber(int roomNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRoom(Room room) {
		
		session.saveOrUpdate(room);
		transaction.commit();
		session.close();
		
		LOGGER.info("Room : " + room + " saved successfully.");
	}

}
