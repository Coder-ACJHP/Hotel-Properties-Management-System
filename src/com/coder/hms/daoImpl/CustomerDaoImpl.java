package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.CustomerDAO;
import com.coder.hms.entities.Customer;

public class CustomerDaoImpl implements CustomerDAO {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public CustomerDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
		session = dataSourceFactory.getSession();

	}
	
	@Override
	public Customer findCustomerByName(String name, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerByDocumentId(long DocumentNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {

		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		List<Customer> customerList = query.getResultList();
		return customerList;
	}

	public void save(Customer theCustomer) {
		session.saveOrUpdate(theCustomer);
		session.getTransaction().commit();
		
	}

}
