/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.connection;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import com.coder.hms.entities.Reservation;
import com.coder.hms.ui.external.InformationFrame;

public class DataSourceFactory {

	private static InformationFrame INFORMATION_FRAME;
	private static SessionFactory sessionFactory = null;

	public DataSourceFactory() {

		INFORMATION_FRAME = new InformationFrame();
	}

	public static synchronized void createConnection() {
		try {

			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("com/coder/hms/connection/hibernate.cfg.xml")
						.addAnnotatedClass(Reservation.class).buildSessionFactory();
			}

		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			INFORMATION_FRAME.setMessage("Database connection error!");
			INFORMATION_FRAME.setVisible(true);
			System.exit(1);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Connection getSqlConnection() {
		Connection connection = null;
		try {
			connection = getSessionFactory().
					getSessionFactoryOptions().getServiceRegistry().
					getService(ConnectionProvider.class).getConnection();
		} catch (SQLException e) {
			INFORMATION_FRAME.setMessage("Converting connection error!");
			INFORMATION_FRAME.setVisible(true);
		}
		return connection;
	}
	
	public void shutDown() {
		if(sessionFactory.isOpen())
			DataSourceFactory.sessionFactory.close();
	}
}
