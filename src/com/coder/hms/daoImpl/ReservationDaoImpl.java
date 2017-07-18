package com.coder.hms.daoImpl;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.ReservationDAO;
import com.coder.hms.entities.Reservation;

public class ReservationDaoImpl implements ReservationDAO{

	private Session session;
	private Transaction transaction;
	private DataSourceFactory dataSourceFactory;
	private final Logger LOGGER = Logger.getLogger(HotelDaoImpl.class.getName());
	
	public ReservationDaoImpl() {
		dataSourceFactory = new DataSourceFactory();
		session = dataSourceFactory.getSession();
		transaction = session.beginTransaction();
	}
	
	@Override
	public Reservation findReservationById(long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation findReservationByDate(String Date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelReservation(long reservationId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reservation> getAllReservations() {
		// TODO Auto-generated method stub
		return null;
	}

}
