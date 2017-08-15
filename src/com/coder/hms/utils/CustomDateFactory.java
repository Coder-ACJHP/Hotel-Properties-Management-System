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
		//for formatting date as desired
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}

	////////////////////////////////////////////////////////
	//this method working on main application date,       //
	//we need to stop changing date after 00:00 o'clock   //
	//because hotel systems audit is between 1 AM and 6 AM//
	//not at midnight, thats why in this method we have to//
	//bring back the date after 00:00 to yesterday.Thats  //
	//mean we will stop the date changing to new day until//
	//as desired time.                                    //
	////////////////////////////////////////////////////////
	public void setValidDateUntilAudit(int counter) {
		///////////////////////////////////////////////////////
		//we need to repeat the method in every second to    //
		//catch if the hour and minutes equals 00:00.That's  //
		//why we gonna using timer schedule                  //
		final Timer timer = new Timer();
		final TimerTask task = new TimerTask() {

			@Override
			public void run() {
				//get hour and minute from calendar
				calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR);
				int min = calendar.get(Calendar.MINUTE);
				int sec = calendar.get(Calendar.SECOND);

				//check if the field value equals -1
				if (counter == -1) {
					//and the time at 00:00
					if (hour == 0 && min == 0 && sec == 2) {
						//bring the date one day back
						calendar.add(Calendar.DATE, counter);
						date = calendar.getTime();
					}
				//////////////////////////////////////////////////////	
				//else return normal date because date not          //
				//initialized that's mean NULL POINTER EXCEPTION!   //
				} else {
					date = new Date();
				}

			}
		};
		/////////////////////////////////////////////////////////
		//repeating in every 100 milliseconds not second because//
		//when we create new object from this class it will    //
		//create in less than one second [running from NULL    //
		// POINTER EXCEPTION!]                                 //
		/////////////////////////////////////////////////////////
		timer.schedule(task, 0, 100);
	}

	//////////////////////////////////////////
	//Get the prepared date as desired pattern//
	public Date getDate() {
		final String today = sdf.format(date);
		final LocalDate ld = LocalDate.parse(today);
		date = java.sql.Date.valueOf(ld);
		return date;
	}
}
