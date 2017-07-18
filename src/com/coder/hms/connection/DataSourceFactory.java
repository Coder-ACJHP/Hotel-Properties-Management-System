package com.coder.hms.connection;

import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.coder.hms.entities.Reservation;

public class DataSourceFactory {

	private Session session = null;
	private SessionFactory sessionFactory = null;
	private final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());
	
	public DataSourceFactory() {
		
		LOGGER.info("Initializing SessionFactory...");
		
		if(sessionFactory == null) {
			sessionFactory = new Configuration().
                    configure("com/coder/hms/connection/hibernate.cfg.xml").
                    addAnnotatedClass(Reservation.class).
                    buildSessionFactory();
			session = sessionFactory.openSession();
			LOGGER.info("SessionFactory created successfully.");
		}
	}

	public Session getSession() {
		LOGGER.info("Returning Session.");
		return session;
	}
	
	public void shutDown() {
		LOGGER.info("Closing SessionFactory...");
		sessionFactory.close();
		session.close();
	}
	
}
