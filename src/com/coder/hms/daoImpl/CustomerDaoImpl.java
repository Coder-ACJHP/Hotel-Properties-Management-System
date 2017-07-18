package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.CustomerDAO;
import com.coder.hms.entities.Customer;

public class CustomerDaoImpl implements CustomerDAO {

	private Session session;
	private Transaction transaction;
	private DataSourceFactory dataSourceFactory;
	
	public CustomerDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		session = dataSourceFactory.getSession();
		transaction = session.beginTransaction();
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
		session.close();
		return customerList;
	}

}
