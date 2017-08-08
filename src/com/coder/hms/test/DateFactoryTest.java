package com.coder.hms.test;

import com.coder.hms.utils.CustomDateFactory;

public class DateFactoryTest {

	public static void main(String[] args) throws InterruptedException {
		
		CustomDateFactory cdf = new CustomDateFactory();
		cdf.getValidDateUntilAudit(-1);
		while(true) {
			Thread.sleep(1000);
			System.out.println(cdf.getDate());
		}
	}
}
