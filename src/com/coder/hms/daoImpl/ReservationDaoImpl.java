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
		Reservation reservation = null;
		
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where id=:theId", Reservation.class);
			query.setParameter("theId", theId);
			query.setMaxResults(1);
			reservation = query.getSingleResult();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("No reservation found!");
			frame.setVisible(true);
		}
		session.close();
		return reservation;
	}
	
	@Override
	public Reservation findSingleReservByThisDate(String Date) {
		Reservation reservation = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:Date", Reservation.class);
			query.setParameter("Date", Date);
			reservation = query.getSingleResult();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation at this date!");
			frame.setVisible(true);
		}
		session.close();	
		return reservation;
	}

	@Override
	public List<Reservation> getReservListByThisDate(String today) {
		List<Reservation> reservList = null;
		
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:today", Reservation.class);
			query.setParameter("today", today);
			reservList = query.getResultList();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("No reservation found!");
			frame.setVisible(true);
		}
		session.close();
		return reservList;
	}
	
	@Override
	public void saveReservation(Reservation reservation) {

		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		session.save(reservation);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Reservation> getAllReservations() {
		List<Reservation> reservList = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation", Reservation.class);
			reservList = query.getResultList();
				
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("No reservation found!");
			frame.setVisible(true);
		}
		session.close();
		return reservList;
	}


	public List<Reservation> getGaranteedReservs(String reservDate) {
		List<Reservation> guaranteedList = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation "
								+ "where bookStatus = 'GUARANTEE' and checkinDate=:today", Reservation.class);
			query.setParameter("today", reservDate);
			guaranteedList = query.getResultList();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation at guaranteed list!");
			frame.setVisible(true);
		}
		session.close();	
		return guaranteedList;
	}

	@Override
	public List<Reservation> getReservsAsWaitlist(String reservDate) {
		List<Reservation> waitedList = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation "
								+ "where bookStatus = 'WAITLIST' and checkinDate=:today", Reservation.class);
			query.setParameter("today", reservDate);
			waitedList = query.getResultList();
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation at waitlist!");
			frame.setVisible(true);
		}
		session.close();	
		return waitedList;
	}

	@Override
	public Reservation getLastReservation() {
		Reservation lastRecord = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation order by Id DESC", Reservation.class);
			query.setMaxResults(1);
			lastRecord = query.getSingleResult();

		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Last reservation not found!");
			frame.setVisible(true);
		}
		session.close();
		return lastRecord;
		
	}

	@Override
	public boolean updateReservation(Reservation reservation) {
		boolean result = false;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			session.update(reservation);
			session.getTransaction().commit();
			result = true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			result = false;
		}
		session.close();
		return result;
	}

	@Override
	public Reservation findReservationByAgencyRefNo(String agencyRefNo) {
		Reservation reservWithAgencyRefNo = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where agencyRefNo=:agencyRefNo",Reservation.class);
			query.setParameter("agencyRefNo", agencyRefNo);
			query.setMaxResults(1);
			reservWithAgencyRefNo = query.getSingleResult();

		} catch (NoResultException e) {
			session.getTransaction().rollback();
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation with this agency referance number!");
			frame.setVisible(true);
		}
		session.close();
		return reservWithAgencyRefNo;
	}
	
	@Override
	public Reservation findReservationByRefNo(String refNo) {
		Reservation reservWithRefNo = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Reservation> query = session.createQuery("from Reservation where referanceNo=:refNo", Reservation.class);
			query.setParameter("refNo", refNo);
			query.setMaxResults(1);
			reservWithRefNo = query.getSingleResult();
			
		} catch (NoResultException e) {
			session.getTransaction().rollback();
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("There is no reservation with this referance number!");
			frame.setVisible(true);
		}
		session.close();
		return reservWithRefNo;

	}
	
	@Override
	public void deleteReservation(long id) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Reservation res = session.load(Reservation.class, id);
		session.delete(res);
		session.flush() ;
		session.getTransaction().commit();
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
