package com.coder.hms.daoImpl;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	public List<Payment> getAllPaymentsByRoomNumber(String theRoomNumber, String string) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Payment> query = session.createQuery("from Payment where "
				+ "roomNumber = :theRoomNumber and dateTime >= :localDate", Payment.class);
		query.setParameter("theRoomNumber", theRoomNumber);
		query.setParameter("localDate", string);
		List<Payment> paymentList = query.getResultList();
		session.close();
		
		return paymentList;
	}

	@Override
	public String getTotalCashDollarForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where "
				+ "paymentType = 'CASH PAYMENT' and currency = 'DOLLAR' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalCashLiraPaymentsForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where "
				+ "paymentType = 'CASH PAYMENT' and currency = 'TURKISH LIRA' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalCashEuroPaymentsForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where "
				+ "paymentType = 'CASH PAYMENT' and currency = 'EURO' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalCashPoundPaymentsForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where "
				+ " paymentType = 'CASH PAYMENT' and currency = 'POUND' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalCreditPaymentsForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Payment where "
				+ "paymentType = 'CREDIT CARD' and currency = 'TURKISH LIRA' and dateTime >= :date", String.class);
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
	public Payment getLastPayment() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Payment> query = session.createQuery("from Payment order by Id DESC", Payment.class);
		query.setMaxResults(1);
		Payment lastRecord = query.getSingleResult();
		session.close();
				if(lastRecord == null) {
					JOptionPane.showMessageDialog(new JFrame(), "Last payment not found!", 
							JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
					return null;
				}
		return lastRecord;
	}
	
	@Override
	public Payment getEarlyPaymentByRoomNumber(String number) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Payment> query = session.createQuery("from Payment where roomNumber = :theRoomNumber and title = 'EARLY PAYMENT'", Payment.class);
		query.setParameter("theRoomNumber", number);
		query.setMaxResults(1);
		Payment payment = query.getSingleResult();
		session.close();
			if(payment == null) {
				JOptionPane.showMessageDialog(new JFrame(), "Early payment not found!", 
						JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
				return null;
			}
		
		return payment;
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
