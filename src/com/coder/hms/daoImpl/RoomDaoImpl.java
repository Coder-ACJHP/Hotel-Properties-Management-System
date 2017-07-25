/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
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

	}
	
	@Override
	public Room getRoomByRoomNumber(String roomNumber) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Room> query = session.createQuery("from Room where number=:roomNumber", Room.class);
		query.setParameter("roomNumber", roomNumber);
		Room room = query.getSingleResult();
		
		LOGGER.info(room.toString());
		
		session.close();
		return room;
	}

	@Override
	public void saveRoom(Room room) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(room);
		session.getTransaction().commit();
		session.close();
	}

	public List<Room> getAllRooms() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Room> query = session.createQuery("from Room", Room.class);
		List<Room> roomList = query.getResultList();
		
		LOGGER.info(roomList.toString());
		
		session.close();
		return roomList;
	}

	public Room getRoomByReservId(long id) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Room> query = session.createQuery("from Room where ReservationId=:id", Room.class);
		query.setParameter("id", id);
		Room room = query.getSingleResult();
		
		LOGGER.info(room.toString());
		
		session.close();
		return room;
	}

}
