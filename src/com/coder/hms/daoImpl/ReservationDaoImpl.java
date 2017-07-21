package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.ReservationDAO;
import com.coder.hms.entities.Reservation;

public class ReservationDaoImpl implements ReservationDAO{

	private Session session;
	private DataSourceFactory dataSourceFactory;
//	private final Logger LOGGER = Logger.getLogger(HotelDaoImpl.class.getName());
	
	public ReservationDaoImpl() {
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
		session = dataSourceFactory.getSession();
		
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
		session.save(reservation);
		session.getTransaction().commit();
	}

	@Override
	public void cancelReservation(long reservationId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reservation> getAllReservations() {
		Query<Reservation> query = session.createQuery("from Reservation", Reservation.class);
		List<Reservation> reservList = query.getResultList();
		return reservList;
	}

}
