/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.ReservationDAO;
import com.coder.hms.entities.Reservation;

public class ReservationDaoImpl implements ReservationDAO{

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public ReservationDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
		
	}
	
	@Override
	public Reservation findReservationById(long theId) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Reservation> query = session.createQuery("from Reservation where id=:theId", Reservation.class);
		query.setParameter("theId", theId);
		Reservation reservation = query.getSingleResult();
		
		
		session.close();
		return reservation;
	}

	@Override
	public Reservation findReservationByDate(String Date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:Date", Reservation.class);
		query.setParameter("Date", Date);
		Reservation reservation = query.getSingleResult();
		
		
		session.close();
		return reservation;
	}

	@Override
	public void saveReservation(Reservation reservation) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(reservation);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void cancelReservation(long reservationId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reservation> getAllReservations() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Reservation> query = session.createQuery("from Reservation", Reservation.class);
		List<Reservation> reservList = query.getResultList();
		session.close();
		
		
		return reservList;
	}

	public List<Reservation> getReservsByDate(String today) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:today", Reservation.class);
		query.setParameter("today", today);
		List<Reservation> reservList = query.getResultList();
		session.close();
		
		
		return reservList;
	}

	public List<Reservation> getGaranteedReservs(String reservDate) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Reservation> query = session.createQuery("from Reservation "
							+ "where bookStatus = 'GUARANTEE' and checkinDate=:today", Reservation.class);
		query.setParameter("today", reservDate);
		List<Reservation> guaranteedList = query.getResultList();
		session.close();
				
		return guaranteedList;
	}

	public List<Reservation> getReservsAsWaitlist(String reservDate) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Reservation> query = session.createQuery("from Reservation "
							+ "where bookStatus = 'WAITLIST' and checkinDate=:today", Reservation.class);
		query.setParameter("today", reservDate);
		List<Reservation> waitedList = query.getResultList();
		session.close();
				
		return waitedList;
	}

	public Reservation getLastReservation() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Reservation> query = session.createQuery("from Reservation order by Id DESC", Reservation.class);
		query.setMaxResults(1);
		Reservation lastRecord = query.getSingleResult();
		session.close();
				
		return lastRecord;
	}

	public Reservation getReservationById(long reservationId) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Reservation> query = session.createQuery("from Reservation where Id=:reservationId", Reservation.class);
		query.setParameter("reservationId", reservationId);
		query.setMaxResults(1);
		Reservation reservationById = query.getSingleResult();
		session.close();
				
		return reservationById;
	}

	@Override
	public boolean updateReservation(Reservation reservation) {
		boolean result = false;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(reservation);
			session.getTransaction().commit();
			session.close();
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

}
