package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Payment;

public interface PaymentDAO {

	public void savePayment(Payment payment);
	
	public boolean deletePayment(long theId);
	
	public Payment getPaymentById(long Id);
		
	public List<Payment> getAllPaymentsByRoomNumber(String roomNumber);
	
	public String getTotalDollarForOneDay(String date);
	
	public String getTotalLiraPaymentsForOneDay(String date);
	
	public String getTotalEuroPaymentsForOneDay(String date);
	
	public String getTotalPoundPaymentsForOneDay(String date);
	
	public String getTotalCreditPaymentsForOneDay(String date);
}
