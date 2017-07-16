package com.coder.hms.dao;


import java.util.List;

import com.coder.hms.entities.Customer;
public interface CustomerDAO {

	public Customer findCustomerByName(String name, String lastName);
	
	public Customer findCustomerByDocumentId(long DocumentNo);
	
	public List<Customer> getAllCustomers(); 

}
