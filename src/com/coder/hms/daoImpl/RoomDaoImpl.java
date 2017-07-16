package com.coder.hms.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.RoomDAO;
import com.coder.hms.entities.Room;

public class RoomDaoImpl implements RoomDAO {

	private DataSourceFactory dataSourceFactory;
	private SessionFactory sessionFactory;
	private Session session;
	
	public RoomDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		sessionFactory = dataSourceFactory.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	}
	
	@Override
	public Room getRoomByRoomNumber(int roomNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRoom(Room room) {
		// TODO Auto-generated method stub

	}

}
