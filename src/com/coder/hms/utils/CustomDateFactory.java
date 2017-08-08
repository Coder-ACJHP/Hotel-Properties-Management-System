package com.coder.hms.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CustomDateFactory {

	private Date date;
	private Timer timer;
	private Calendar calendar;
	private SimpleDateFormat sdf;

	
	public CustomDateFactory() {
		timer = new Timer();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public synchronized void getValidDateUntilAudit(int counter) {
	
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR);
				int min = calendar.get(Calendar.MINUTE);
				
				if(counter == -1) {
					
					if(hour == 5 && min == 16) {
						calendar.add(Calendar.DATE, counter);
						date = calendar.getTime();
					}
					else {
						date = new Date();
					}
					
				}
				else {
					date = new Date();
				}
			}
		};timer.schedule(task, 0, 1000);		
	}

	public Date getDate() {
		final String today = sdf.format(date);
		final LocalDate ld = LocalDate.parse(today);
		date = java.sql.Date.valueOf(ld);		
		return date;
	}
}
