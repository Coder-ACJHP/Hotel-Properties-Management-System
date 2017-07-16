package com.coder.hms.connection;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.coder.hms.entities.Reservation;

public class DataSourceFactory {

	private SessionFactory sessionFactory = null;
	private final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());
	
	public DataSourceFactory() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration().
                    configure("com/coder/hms/connection/hibernate.cfg.xml").
                    addAnnotatedClass(Reservation.class).
                    buildSessionFactory();
			
			LOGGER.log(Level.FINE, "SESSION FACTORY CREATED.");
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void close() {
		sessionFactory.close();
	}
	
}
