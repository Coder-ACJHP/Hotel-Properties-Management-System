package com.coder.hms.beans;

import java.util.Date;

import com.coder.hms.utils.CustomDateFactory;

public class DateBean {

	private static DateBean instance = null;
	private static CustomDateFactory dateFactory;

	public static DateBean getInstance() {
		
		if (instance == null) {
			instance = new DateBean();
			
		}
		dateFactory = new CustomDateFactory();
		return instance;
	}

	public static Date getDate() {
		return dateFactory.getDate();
	}

	public void setAuditStatus(boolean isAudit) {
		dateFactory.setValidDateUntilAudit(isAudit);
	}
}
