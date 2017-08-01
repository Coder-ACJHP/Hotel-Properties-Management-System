package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.PaymentDAO;
import com.coder.hms.entities.Payment;

public class PaymentDaoImpl implements PaymentDAO {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public PaymentDaoImpl() {
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
	}
	
	@Override
	public void savePayment(Payment payment) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(payment);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deletePayment(long theId) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("delete Payment where id = :theId");
		query.setParameter("theId", theId);
		query.executeUpdate();
		session.close();

	}

	@Override
	public Payment getPaymentById(long Id) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Payment payment = session.get(Payment.class, Id);
		session.close();
		return payment;
	}

	@Override
	public List<Payment> getAllPaymentsByRoomNumber(String theRoomNumber) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Payment> query = session.createQuery("from Payment where roomNumber = :theRoomNumber", Payment.class);
		query.setParameter("theRoomNumber", theRoomNumber);
		List<Payment> paymentList = query.getResultList();
		session.close();
		return paymentList;
	}

}
