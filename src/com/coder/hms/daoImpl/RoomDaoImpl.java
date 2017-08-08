/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.RoomDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Room;

public class RoomDaoImpl implements RoomDAO, TransactionManagement {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public RoomDaoImpl() {

		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();

	}
	
	@Override
	public Room getRoomByRoomNumber(String roomNumber) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Room> query = session.createQuery("from Room where number=:roomNumber", Room.class);
		query.setParameter("roomNumber", roomNumber);
		Room room = query.getSingleResult();
		session.close();
				
		return room;
	}

	@Override
	public void saveRoom(Room room) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		session.saveOrUpdate(room);
		session.getTransaction().commit();
		session.close();
		
	}

	public List<Room> getAllRooms() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Room> query = session.createQuery("from Room", Room.class);
		List<Room> roomList = query.getResultList();
		session.close();
				
		return roomList;
	}

	public Room getRoomByReservId(long id) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Room> query = session.createQuery("from Room where ReservationId=:id", Room.class);
		query.setParameter("id", id);
		Room room = query.getSingleResult();
		session.close();
				
		return room;
	}

	public void setAllRoomsAtClean(String clean) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus=:clean");
		query.setParameter("clean", clean);
		query.executeUpdate();
		session.close();
		
	}

	public void setSingleRoomAsCleanByRoomNumber(String rowData) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus = 'CLEAN' where number=:rowData");
		query.setParameter("rowData", rowData);
		query.executeUpdate();		
		session.close();
		
	}

	public void setRoomCheckedOut(String num) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		final String HQL = "UPDATE Room SET usageStatus = 'EMPTY', personCount = 0, price = 0, "
				+ "totalPrice = 0, balance = '0', customerGrupName = '', currency = '', remainingDebt = 0, ReservationId = 0 where number=:num";
		Query<?> query = session.createQuery(HQL);
		query.setParameter("num", num);
		query.executeUpdate();		
		session.close();
		
	}

	public void setAllRoomsAtDirty(String dirty) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus=:dirty");
		query.setParameter("dirty", dirty);
		query.executeUpdate();
		session.close();
		
	}
	
	public void setSingleRoomAsDirtyByRoomNumber(String roomNumber) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus = 'DIRTY' where number=:roomNumber");
		query.setParameter("roomNumber", roomNumber);
		query.executeUpdate();		
		session.close();
		
	}

	public void setSingleRoomAsDNDByRoomNumber(String roomNumber) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus = 'DND' where number=:roomNumber");
		query.setParameter("roomNumber", roomNumber);
		query.executeUpdate();		
		session.close();
		
	}

	public void setRoomAtDND(String roomNumber) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus = 'DND' where number=:roomNumber");
		query.setParameter("roomNumber", roomNumber);
		query.executeUpdate();		
		session.close();
		
	}

	@Override
	public void beginTransactionIfAllowed(Session theSession) {
		if(!theSession.getTransaction().isActive()) {
			theSession.beginTransaction();	
		}else {
			theSession.getTransaction().rollback();
			theSession.beginTransaction();
		}
		
	}


}
