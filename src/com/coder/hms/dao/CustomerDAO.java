/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.dao;


import java.util.List;

import com.coder.hms.entities.Customer;
public interface CustomerDAO {

	public Customer findCustomerByName(String name, String lastName);

	public Customer findCustomerByDocumentId(long DocumentNo);

	public List<Customer> getAllCustomers();

	public boolean update(Customer customer);

	public void deleteCustomerByReservationId(long id);

	public Customer getSinlgeCustomerByReservId(long id, String name);
}
