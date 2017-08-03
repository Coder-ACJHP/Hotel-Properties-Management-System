/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.CustomerDAO;
import com.coder.hms.entities.Customer;

public class CustomerDaoImpl implements CustomerDAO {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	private final Logger LOGGER = Logger.getLogger(CustomerDaoImpl.class.getName());
	
	public CustomerDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();

	}
	
	@Override
	public Customer findCustomerByName(String name, String lastName) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Customer> query = session.createQuery("from Customer where FirstName=:name and LastName=:lastName", Customer.class);
		query.setParameter("name", name);
		query.setParameter("lastName", lastName);
		
		final Customer customer = query.getSingleResult();
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
		session.beginTransaction();
		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		List<Customer> customerList = query.getResultList();
		session.close();
		LOGGER.info(customerList.toString());
		
		return customerList;
	}

	public boolean save(Customer theCustomer) {
		 boolean success = false;
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(theCustomer);
			session.getTransaction().commit();
			session.close();
			success = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	public List<Customer> getCustomerByReservId(long id) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Customer> query = session.createQuery("from Customer where ReservationId=:id", Customer.class);
		query.setParameter("id", id);
		List<Customer> customerList = query.getResultList();
		session.close();
		LOGGER.info(customerList.toString());
		
		return customerList;
	}

}
