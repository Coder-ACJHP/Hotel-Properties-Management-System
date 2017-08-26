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
import com.coder.hms.dao.CustomerDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Customer;
import com.coder.hms.ui.external.InformationFrame;

public class CustomerDaoImpl implements CustomerDAO, TransactionManagement {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public CustomerDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();

	}
	
	@Override
	public Customer findCustomerByName(String name, String lastName) {
		Customer customer = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer where FirstName=:name and LastName=:lastName", Customer.class);
			query.setParameter("name", name);
			query.setParameter("lastName", lastName);
			
			customer = query.getSingleResult();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Customers not found!");
			frame.setVisible(true);
		}
		session.close();
		return customer;
	}

	@Override
	public Customer findCustomerByDocumentId(long DocumentNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		List<Customer> customerList = query.getResultList();
		session.close();
		
		return customerList;
	}

	@Override
	public boolean save(Customer theCustomer) {
		 boolean success = false;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			session.save(theCustomer);
			session.getTransaction().commit();
		
			success = true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			success = false;
		}
		session.close();
		return success;
	}

	@Override
	public boolean update(Customer theCustomer) {
		 boolean success = false;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			session.update(theCustomer);
			session.getTransaction().commit();
			session.close();
			
			success = true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			success = false;
		}
		return success;
	}
	
	public List<Customer> getCustomerByReservId(long id) {
		List<Customer> customerList = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer where ReservationId=:id", Customer.class);
			query.setParameter("id", id);
			customerList = query.getResultList();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Customers not found!");
			frame.setVisible(true);
		}
		session.close();
		return customerList;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public void deleteCustomerByReservationId(long id) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query query = session.createQuery("delete from Customers where ReservationId=:id");
		query.setParameter("id", id);
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Override
	public Customer getSinlgeCustomerByReservId(long id) {
		Customer customer = null;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer where ReservationId=:theId", Customer.class);
			query.setParameter("theId", id);
			
			customer = query.getSingleResult();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Customers not found!");
			frame.setVisible(true);
		}
		session.close();
		return customer;
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
