/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.coder.hms.daoImpl.PaymentDaoImpl;

public class HibernateConnection {

	public static void main(String[] args) {

			PaymentDaoImpl paymentDaoImpl = new PaymentDaoImpl();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Calendar date = Calendar.getInstance();
			date.set(Calendar.HOUR_OF_DAY, 0);
			final String today = sdf.format(date.getTime());
			
			System.out.println(paymentDaoImpl.getTotalCashDollarForOneDay(today));
			System.out.println(paymentDaoImpl.getTotalCashEuroPaymentsForOneDay(today));
			System.out.println(paymentDaoImpl.getTotalCashPoundPaymentsForOneDay(today));
			System.out.println(paymentDaoImpl.getTotalCashLiraPaymentsForOneDay(today));

		
	}

}
