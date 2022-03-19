package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Payment;

public interface PaymentDAO {

	public void savePayment(Payment payment);
	
	public boolean deletePayment(long theId);
	
	public Payment getPaymentById(long Id);
		
	public List<Payment> getAllPaymentsByRoomNumber(String roomNumber, String localDate);
	
	public Payment getEarlyPaymentByRoomNumber(String number);
	
	public Payment getLastPayment();
}
