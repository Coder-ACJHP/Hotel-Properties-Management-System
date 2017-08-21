package com.coder.hms.beans;

import java.io.Serializable;
import java.util.Locale;

public class LocaleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LocaleBean instance = null;
	private static Locale locale;
	
	public LocaleBean() {}
	
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
