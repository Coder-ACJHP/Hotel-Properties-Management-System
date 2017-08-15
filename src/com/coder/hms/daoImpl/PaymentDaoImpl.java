package com.coder.hms.daoImpl;

import java.util.List;

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
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		session.saveOrUpdate(payment);
		session.getTransaction().commit();
		session.close();
		
	}


	@Override
	public boolean deletePayment(long theId) {
		boolean result = false;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<?> query = session.createQuery("delete Payment where id = :theId");
			query.setParameter("theId", theId);
			query.executeUpdate();
			session.close();
			
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

	@Override
	public Payment getPaymentById(long Id) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Payment payment = session.get(Payment.class, Id);
		session.close();
		
		return payment;
	}

	@Override
	public List<Payment> getAllPaymentsByRoomNumber(String theRoomNumber) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Payment> query = session.createQuery("from Payment where roomNumber = :theRoomNumber", Payment.class);
		query.setParameter("theRoomNumber", theRoomNumber);
		List<Payment> paymentList = query.getResultList();
		session.close();
		
		return paymentList;
	}

	@Override
	public String getTotalDollarForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where currency = 'DOLLAR' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalLiraPaymentsForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where currency = 'TURKISH LIRA' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalEuroPaymentsForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where currency = 'EURO' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalPoundPaymentsForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where currency = 'POUND' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalCreditPaymentsForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where paymentType = 'CASH PAYMENT', currency = 'TURKISH LIRA' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}
	

	public List<Payment> getAllPaymentsForToday(String today) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Payment> query = session.createQuery("from Payment where dateTime >= :today", Payment.class);
		query.setParameter("today", today);
		List<Payment> paymentList = query.getResultList();
		session.close();
		
		return paymentList;
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
