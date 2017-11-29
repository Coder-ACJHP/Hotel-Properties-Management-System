package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Payment;

public interface PaymentDAO {

	public void savePayment(Payment payment);
	
	public boolean deletePayment(long theId);
	
	public Payment getPaymentById(long Id);
		
	public List<Payment> getAllPaymentsByRoomNumber(String roomNumber, String localDate);
	
	public String getTotalCashDollarForOneDay(String date);
	
	public String getTotalCashLiraPaymentsForOneDay(String date);
	
	public String getTotalCashEuroPaymentsForOneDay(String date);
	
	public String getTotalCashPoundPaymentsForOneDay(String date);
	
	public String getTotalCreditLiraPaymentsForOneDay(String date);
        
        public String getTotalCreditDollarPaymentsForOneDay(String date);
        
        public String getTotalCreditEuroPaymentsForOneDay(String date);
        
        public String getTotalCreditPoundPaymentsForOneDay(String date);
	
	public Payment getEarlyPaymentByRoomNumber(String number);
	
	public Payment getLastPayment();
}
