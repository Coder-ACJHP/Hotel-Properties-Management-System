package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.ReservationDAO;
import com.coder.hms.entities.Reservation;

public class ReservationDaoImpl implements ReservationDAO{

	private DataSourceFactory dataSourceFactory;
	private SessionFactory sessionFactory;
	private Session session;
	
	public ReservationDaoImpl() {
		dataSourceFactory = new DataSourceFactory();
		sessionFactory = dataSourceFactory.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
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
