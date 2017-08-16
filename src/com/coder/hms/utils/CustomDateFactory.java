package com.coder.hms.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class CustomDateFactory {

	private Date date = null;
	private Calendar calendar;
	private SimpleDateFormat sdf;

	public CustomDateFactory() {
		
		//for formatting date as desired
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		//get hour and minute from calendar
		calendar = Calendar.getInstance();
		calendar.setTime(new Date());
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
	public void setValidDateUntilAudit(boolean isAuditted) {

				//check if the field value equals -1
				if (calendar.get(Calendar.HOUR_OF_DAY) <= 6 && isAuditted == false) {
					
						//bring the date one day back
						calendar.add(Calendar.DATE, -1);
						date = calendar.getTime();
						System.out.println("ILK IHTIMAL");
				}
				
				else if(calendar.get(Calendar.HOUR_OF_DAY) > 6 && isAuditted == true){
					
					isAuditted = false;
					date = new Date();

					System.out.println("IKINCI IHTIMAL");
				}
				
				else if(isAuditted) {
					
					ImageIcon icon = new ImageIcon(getClass().getResource("/com/coder/hms/icons/dialogPane_question.png"));
					
					int choosedVal = JOptionPane.showOptionDialog(null, "You're doing early audit, are you sure about this?",
							"Approving question", 0, JOptionPane.YES_NO_OPTION, icon, null, null);
					
					if(choosedVal == JOptionPane.YES_OPTION) {
						date = new Date();
					}
					
					else {
						isAuditted = false;
						return;
					}
					
				}
				
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
