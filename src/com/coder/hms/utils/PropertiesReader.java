package com.coder.hms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesReader {

	private String userName;
	private String password;
	private String hotelName;
	final static String filePath = "src/com/coder/hms/languageFiles/Credentials.properties";
	
	
	public PropertiesReader() {
		
		final Properties properties = new Properties();
		try {
			
			final InputStream inputStream = new FileInputStream(filePath);
			properties.load(inputStream);
			userName = properties.getProperty("system.username");
			password = properties.getProperty("system.password");
			hotelName = properties.getProperty("system.hotelname");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean checkIsAdministrator(String inputName, String inputPwd) {
		
		if (inputName.equals(userName) || inputPwd.equals(password))
			return true;
		else 
			return false;
	}
	
	public String getHotelName() {
		return hotelName;
	}
	
}
