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

		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer where FirstName=:name and LastName=:lastName", Customer.class);
			query.setParameter("name", name);
			query.setParameter("lastName", lastName);
			
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Customers not found!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public Customer findCustomerByDocumentId(long DocumentNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
	
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer", Customer.class);
			return query.getResultList();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}

	}

	@Override
	public boolean save(Customer theCustomer) {

		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.save(theCustomer);
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
	public boolean update(Customer theCustomer) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.update(theCustomer);
			session.getTransaction().commit();
			
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	public List<Customer> getCustomerByReservId(long id) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer where ReservationId=:id", Customer.class);
			query.setParameter("id", id);
			return query.getResultList();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Customers not found!");
			frame.setVisible(true);
			return null;
		} finally {
			session.close();
		}
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public void deleteCustomerByReservationId(long id) {
		
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query query = session.createQuery("delete from Customers where ReservationId=:id");
			query.setParameter("id", id);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		
	}
	
	@Override
	public Customer getSinlgeCustomerByReservId(long id) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer where ReservationId=:theId", Customer.class);
			query.setParameter("theId", id);
			
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Customers not found!");
			frame.setVisible(true);
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
