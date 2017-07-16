package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.CustomerDAO;
import com.coder.hms.entities.Customer;

public class CustomerDaoImpl implements CustomerDAO {

	private DataSourceFactory dataSourceFactory;
	private SessionFactory sessionFactory;
	private Session session;
	
	public CustomerDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		sessionFactory = dataSourceFactory.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
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
		// TODO Auto-generated method stub
		return null;
	}

}
