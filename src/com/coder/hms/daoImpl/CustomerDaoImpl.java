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
import com.coder.hms.utils.LoggingEngine;

public class CustomerDaoImpl implements CustomerDAO, TransactionManagement {

	private Session session;
        private static LoggingEngine logging;
	private DataSourceFactory dataSourceFactory;
	
	public CustomerDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
                logging = LoggingEngine.getInstance();

	}
	
	@Override
	public Customer findCustomerByName(String name, String lastName) {
                Customer customer = null;
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer where FirstName=:name and LastName=:lastName", Customer.class);
			query.setParameter("name", name);
			query.setParameter("lastName", lastName);
			query.setMaxResults(1);
                        
			customer = query.getSingleResult();
			logging.setMessage("CustomerDaoImpl -> fetching customer with name :" + name);
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Customers not found! :" +e.getLocalizedMessage());
			frame.setVisible(true);
		} finally {
			session.close();
		}
                return customer;
	}

	@Override
	public Customer findCustomerByDocumentId(long DocumentNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer", Customer.class);
			List<Customer> customerList =  query.getResultList();
            session.close();            
			logging.setMessage("CustomerDaoImpl -> fetching all customers");
			
          return customerList;
	}

    @Override
    public boolean save(Customer theCustomer) {

        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            session.save(theCustomer);
            session.getTransaction().commit();
            
            logging.setMessage("CustomerDaoImpl -> customer saved successfully : "+theCustomer.getFirstName());
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("CustomerDaoImpl -> save customer error -> "+e.getLocalizedMessage());
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
			
                        logging.setMessage("CustomerDaoImpl -> customer updated successfully : "+theCustomer.toString());
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
                        logging.setMessage("CustomerDaoImpl -> update customer error -> "+e.getLocalizedMessage());
			return false;
		} finally {
			session.close();
		}
	}
	
	public List<Customer> getCustomerByReservId(long id) {
		List<Customer> customersList = null;
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer where ReservationId=:id", Customer.class);
			query.setParameter("id", id);
			customersList = query.getResultList();
			
                        logging.setMessage("CustomerDaoImpl -> customer updated successfully : "+customersList.toString());
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Customers not found!");
			frame.setVisible(true);
		} finally {
			session.close();
		}
                return customersList;
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
			logging.setMessage("CustomerDaoImpl -> customer deleted successfully");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
                        logging.setMessage("CustomerDaoImpl -> delete customer error -> "+e.getLocalizedMessage());
		} finally {
			session.close();
		}
		
	}
	
	@Override
	public Customer getSinlgeCustomerByReservId(long id, String name) {
		Customer theCustomer = null;
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Customer> query = session.createQuery("from Customer where ReservationId=:theId and FirstName=:name", Customer.class);
			query.setParameter("theId", id);
			query.setParameter("name", name);
			query.setMaxResults(1);
			
			theCustomer = query.getSingleResult();
			logging.setMessage("CustomerDaoImpl -> fetched customer successfully :"+theCustomer.toString());
                        
		} catch (NoResultException e) {
			final InformationFrame frame = new InformationFrame();
			frame.setMessage("Customers not found!");
			frame.setVisible(true);
		} finally {
			session.close();
		}
           return theCustomer;
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
