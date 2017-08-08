package com.coder.hms.entities;

import java.util.Date;

public class DateBean {

	private static Date date;
	private static DateBean instance = null;

	public static DateBean getInstance() {
		if (instance == null) {
			instance = new DateBean();
		}
		return instance;
	}

	public static Date getDate() {
		return date;
	}

	public static void setDate(Date date) {
		DateBean.date = date;
	}
}
