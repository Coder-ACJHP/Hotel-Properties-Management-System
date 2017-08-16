package com.coder.hms.test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFactoryTest {

	public static void main(String[] args) throws InterruptedException {
		
		ZonedDateTime zdt = ZonedDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String date = formatter.format(zdt.toLocalDateTime());
		System.out.println(date);

	}
}
