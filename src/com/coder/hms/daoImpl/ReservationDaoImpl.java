/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.ReservationDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Reservation;

public class ReservationDaoImpl implements ReservationDAO, TransactionManagement {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public ReservationDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
		
	}
	
	@Override
	public Reservation findReservationById(long theId) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("from Reservation where id=:theId", Reservation.class);
		query.setParameter("theId", theId);
		query.setMaxResults(1);
		Reservation reservation = query.getSingleResult();
		session.close();
			if(reservation == null) {
				JOptionPane.showMessageDialog(new JFrame(), "Reservation not found!", 
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return null;
			}
		return reservation;
	}
	
	@Override
	public Reservation findSingleReservByThisDate(String Date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:Date", Reservation.class);
		query.setParameter("Date", Date);
		Reservation reservation = query.getSingleResult();
		session.close();
			if(reservation == null) {
				JOptionPane.showMessageDialog(new JFrame(), "There is no reservation at this date!", 
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return null;
			}
		return reservation;
	}

	@Override
	public List<Reservation> getReservListByThisDate(String today) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:today", Reservation.class);
		query.setParameter("today", today);
		List<Reservation> reservList = query.getResultList();
		session.close();
			if(reservList == null) {
				JOptionPane.showMessageDialog(new JFrame(), "There is no reservation as today date!", 
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return null;
			}
		return reservList;
	}
	
	@Override
	public void saveReservation(Reservation reservation) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		session.saveOrUpdate(reservation);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void cancelReservation(long reservationId) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("update Reservation "
				+ "set bookStatus = 'CANCELLED' where where id=:theId",Reservation.class);
		query.setParameter("theId", reservationId);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public List<Reservation> getAllReservations() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("from Reservation", Reservation.class);
		List<Reservation> reservList = query.getResultList();
		session.close();	
			if(reservList == null) {
				JOptionPane.showMessageDialog(new JFrame(), "There is no reservation!", 
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return null;
			}
		return reservList;
	}


	public List<Reservation> getGaranteedReservs(String reservDate) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("from Reservation "
							+ "where bookStatus = 'GUARANTEE' and checkinDate=:today", Reservation.class);
		query.setParameter("today", reservDate);
		List<Reservation> guaranteedList = query.getResultList();
		session.close();
			if(guaranteedList == null) {
				JOptionPane.showMessageDialog(new JFrame(), "There is no reservation at guaranteed list!", 
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return null;
			}
		return guaranteedList;
	}

	@Override
	public List<Reservation> getReservsAsWaitlist(String reservDate) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("from Reservation "
							+ "where bookStatus = 'WAITLIST' and checkinDate=:today", Reservation.class);
		query.setParameter("today", reservDate);
		List<Reservation> waitedList = query.getResultList();
		session.close();
			if(waitedList == null) {
				JOptionPane.showMessageDialog(new JFrame(), "There is no reservation at waitlist!", 
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return null;
			}
		return waitedList;
	}

	@Override
	public Reservation getLastReservation() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("from Reservation order by Id DESC", Reservation.class);
		query.setMaxResults(1);
		Reservation lastRecord = query.getSingleResult();
		session.close();
				if(lastRecord == null) {
					JOptionPane.showMessageDialog(new JFrame(), "Last reservation not found!", 
							JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
					return null;
				}
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
			session.close();
			result = true;
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), 
					JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
			result = false;
		}
		
		return result;
	}

	@Override
	public Reservation findReservationByAgencyRefNo(String agencyRefNo) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("from Reservation where agencyRefNo=:agencyRefNo",
				Reservation.class);
		query.setParameter("agencyRefNo", agencyRefNo);
		query.setMaxResults(1);
		Reservation reservWithAgencyRefNo = query.getSingleResult();
		session.close();

		if (reservWithAgencyRefNo == null) {
			JOptionPane.showMessageDialog(null, "There is no reservation with this \nagency referance number!",
					JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
			return null;
		} else {
			return reservWithAgencyRefNo;
		}

	}
	
	@Override
	public Reservation findReservationByRefNo(String refNo) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Reservation> query = session.createQuery("from Reservation where referanceNo=:refNo", Reservation.class);
		query.setParameter("refNo", refNo);
		query.setMaxResults(1);
		Reservation reservWithRefNo = query.getSingleResult();
		session.close();
		if (reservWithRefNo == null) {
			JOptionPane.showMessageDialog(null, "There is no reservation with this \nreferance number!",
					JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
			return null;
		} else {
			return reservWithRefNo;
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
