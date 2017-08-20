package com.coder.hms.beans;

import java.util.Locale;

public class LocaleBean {

	private static LocaleBean instance = null;
	private static Locale locale;
	
	public static LocaleBean getInstance() {
		if(instance == null) {
			instance = new LocaleBean();
		}
		return instance;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		LocaleBean.locale = locale;
	}
	
	
}
