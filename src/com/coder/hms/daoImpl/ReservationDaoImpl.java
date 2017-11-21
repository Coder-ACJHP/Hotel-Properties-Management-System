/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.ReservationDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Reservation;
import com.coder.hms.ui.external.InformationFrame;

public class ReservationDaoImpl implements ReservationDAO, TransactionManagement {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public ReservationDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
		
	}
	
	@Override
	public Reservation findReservationById(long theId) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where id=:theId", Reservation.class);
			query.setParameter("theId", theId);
			query.setMaxResults(1);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("No reservation found!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
	}
	
	@Override
	public Reservation findSingleReservByThisDate(String Date) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:Date", Reservation.class);
			query.setParameter("Date", Date);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation at this date!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reservation> getReservListByThisDate(String today) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:today", Reservation.class);
			query.setParameter("today", today);
			return query.getResultList();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("No reservation found!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
	}
	
	@Override
	public void saveReservation(Reservation reservation) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.save(reservation);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		session.close();

	}

	@Override
	public List<Reservation> getAllReservations() {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation", Reservation.class);
			return query.getResultList();
				
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("No reservation found!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
	}


	public List<Reservation> getGaranteedReservs(String reservDate) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation "
								+ "where bookStatus = 'GUARANTEE' and checkinDate=:today", Reservation.class);
			query.setParameter("today", reservDate);
			return query.getResultList();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation at guaranteed list!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Reservation> getReservsAsWaitlist(String reservDate) {
	
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation "
								+ "where bookStatus = 'WAITLIST' and checkinDate=:today", Reservation.class);
			query.setParameter("today", reservDate);
			return query.getResultList();
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation at waitlist!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public Reservation getLastReservation() {
	
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation order by Id DESC", Reservation.class);
			query.setMaxResults(1);
			return query.getSingleResult();

		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Last reservation not found!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
		
	}

	@Override
	public boolean updateReservation(Reservation reservation) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.update(reservation);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}

	}

	@Override
	public Reservation findReservationByAgencyRefNo(String agencyRefNo) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where agencyRefNo=:agencyRefNo",Reservation.class);
			query.setParameter("agencyRefNo", agencyRefNo);
			query.setMaxResults(1);
			return query.getSingleResult();

		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation with this agency referance number!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
	}
	
	@Override
	public Reservation findReservationByRefNo(String refNo) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where referanceNo=:refNo", Reservation.class);
			query.setParameter("refNo", refNo);
			query.setMaxResults(1);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation with this referance number!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
		
		
	}
	
	@Override
	public void deleteReservation(long id) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Reservation res = session.load(Reservation.class, id);
			session.delete(res);
			session.flush() ;
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
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
