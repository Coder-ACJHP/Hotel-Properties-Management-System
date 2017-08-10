package com.coder.hms.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class CustomDateFactory {

	private Date date;
	private Calendar calendar;
	private SimpleDateFormat sdf;

	public CustomDateFactory() {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}

	public void getValidDateUntilAudit(int counter) {

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR);
				int min = calendar.get(Calendar.MINUTE);
				int sec = calendar.get(Calendar.SECOND);

//				System.out.println("HOUR : " + hour + " MINUTE : " + min + " SECOND : " + sec);

				if (counter == -1) {
					if (hour == 8 && min == 1 && sec == 2) {
						calendar.add(Calendar.DATE, counter);
						date = calendar.getTime();
					}
				} else {
					date = new Date();
				}

			}
		};
		timer.schedule(task, 0, 100);
	}

	public Date getDate() {
		final String today = sdf.format(date);
		final LocalDate ld = LocalDate.parse(today);
		date = java.sql.Date.valueOf(ld);
		return date;
	}
}
