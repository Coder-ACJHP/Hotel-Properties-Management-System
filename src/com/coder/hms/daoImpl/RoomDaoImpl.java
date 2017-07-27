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

	@SuppressWarnings("rawtypes")
	public void setAllRoomsAtClean(String clean) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("UPDATE Room SET cleaningStatus=:clean");
		query.setParameter("clean", clean);
		query.executeUpdate();
		session.close();
	}

	@SuppressWarnings("rawtypes")
	public void setSingleRoomAsCleanByRoomNumber(String rowData) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("UPDATE Room SET cleaningStatus = 'CLEAN' where number=:rowData");
		query.setParameter("rowData", rowData);
		query.executeUpdate();		
		session.close();
		
	}

	@SuppressWarnings("rawtypes")
	public void setRoomCheckedOut(String num) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		final String HQL = "UPDATE Room SET cleaningStatus = 'CLEAN', usageStatus = 'EMPTY', personCount = 0, price = 0 where number=:num";
		Query query = session.createQuery(HQL);
		query.setParameter("num", num);
		query.executeUpdate();		
		session.close();
		
	}

	@SuppressWarnings("rawtypes")
	public void setSingleRoomAsDirtyByRoomNumber(String rowData) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("UPDATE Room SET cleaningStatus = 'DIRTY' where number=:rowData");
		query.setParameter("rowData", rowData);
		query.executeUpdate();		
		session.close();
		
	}

}
