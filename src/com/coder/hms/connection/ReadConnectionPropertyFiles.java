/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ReadConnectionPropertyFiles {

	private String JDBC_URL;
	private String USERNAME;
	private String PASSWORD;
	private String DRIVER_CLASS;

	private final Logger logger;
	
	public ReadConnectionPropertyFiles() {
		logger = Logger.getLogger(getClass().getName());
	}
	
	public void initializeDependencies() {
		
		final Properties properties = new Properties();
		try {
			
			final InputStream inputStream = new FileInputStream("src/com/coder/hms/utils/connection.properties");
			properties.load(inputStream);

			this.DRIVER_CLASS = properties.getProperty("get.driverClass");
			this.JDBC_URL = properties.getProperty("get.jdbcUrl");
			this.USERNAME = properties.getProperty("get.user");
			this.PASSWORD = properties.getProperty("get.password");
			
			logger.log(Level.FINE, "Loading connection addresses...");
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					JOptionPane.MESSAGE_PROPERTY, JOptionPane.WARNING_MESSAGE);
			logger.log(Level.WARNING, e.getMessage());

		}
	}

	public String getJDBC_URL() {
		return this.JDBC_URL;
	}

	public String getUSERNAME() {
		return this.USERNAME;
	}

	public String getPASSWORD() {
		return this.PASSWORD;
	}

	public String getDRIVER_CLASS() {
		return this.DRIVER_CLASS;
	}

	
}
