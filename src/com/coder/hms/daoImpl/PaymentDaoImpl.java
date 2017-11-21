package com.coder.hms.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.PaymentDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Payment;

public class PaymentDaoImpl implements PaymentDAO, TransactionManagement {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public PaymentDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
	}
	
	@Override
	public void savePayment(Payment payment) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.saveOrUpdate(payment);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}


	@Override
	public boolean deletePayment(long theId) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Payment payment = session.get(Payment.class, theId);
			session.delete(payment);
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
	public Payment getPaymentById(long Id) {
		
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			return session.get(Payment.class, Id);
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<Payment> getAllPaymentsByRoomNumber(String theRoomNumber, String string) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Payment> query = session.createQuery("from Payment where "
					+ "roomNumber = :theRoomNumber and dateTime >= :localDate", Payment.class);
			query.setParameter("theRoomNumber", theRoomNumber);
			query.setParameter("localDate", string);
			return query.getResultList();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
		
	}

	@Override
	public String getTotalCashDollarForOneDay(String date) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery("select sum(price) from Payment where "
					+ "paymentType = 'CASH PAYMENT' and currency = 'DOLLAR' and dateTime >= :date", String.class);
			query.setParameter("date", date);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public String getTotalCashLiraPaymentsForOneDay(String date) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery("select sum(price) from Payment where "
					+ "paymentType = 'CASH PAYMENT' and currency = 'TURKISH LIRA' and dateTime >= :date", String.class);
			query.setParameter("date", date);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public String getTotalCashEuroPaymentsForOneDay(String date) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery("select sum(price) from Payment where "
					+ "paymentType = 'CASH PAYMENT' and currency = 'EURO' and dateTime >= :date", String.class);
			query.setParameter("date", date);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public String getTotalCashPoundPaymentsForOneDay(String date) {
		
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery("select sum(price) from Payment where "
					+ " paymentType = 'CASH PAYMENT' and currency = 'POUND' and dateTime >= :date", String.class);
			query.setParameter("date", date);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public String getTotalCreditPaymentsForOneDay(String date) {

		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery(
					"select sum(price) from Payment where "
							+ "paymentType = 'CREDIT CARD' and currency = 'TURKISH LIRA' and dateTime >= :date",
					String.class);
			query.setParameter("date", date);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}
	

	public List<Payment> getAllPaymentsForToday(String today) {
		
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Payment> query = session.createQuery("from Payment where dateTime >= :today", Payment.class);
			query.setParameter("today", today);
			return query.getResultList();

		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public Payment getLastPayment() {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Payment> query = session.createQuery("from Payment order by Id DESC", Payment.class);
			query.setMaxResults(1);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}
	
	@Override
	public Payment getEarlyPaymentByRoomNumber(String number) {

		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Payment> query = session.createQuery(
					"from Payment where roomNumber = :theRoomNumber and title = 'EARLY PAYMENT'", Payment.class);
			query.setParameter("theRoomNumber", number);
			query.setMaxResults(1);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
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
