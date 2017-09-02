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

	private static SessionFactory sessionFactory = null;

	public DataSourceFactory() {

		
	}

	public static synchronized void createConnection() {
		try {

			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("com/coder/hms/connection/hibernate.cfg.xml")
						.addAnnotatedClass(Reservation.class).buildSessionFactory();
			}

		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			InformationFrame INFORMATION_FRAME = new InformationFrame();
			INFORMATION_FRAME.setMessage("Sorry we can't connect to database right now, without "
					+ "connection the application will not work properly.");
			INFORMATION_FRAME.okBtn.addActionListener(ActionListener->{
				System.exit(1);
			});
			INFORMATION_FRAME.setVisible(true);
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
			InformationFrame INFORMATION_FRAME = new InformationFrame();
			INFORMATION_FRAME.setMessage("Connection converting error!");
			INFORMATION_FRAME.setVisible(true);
		}
		return connection;
	}
	
	public void shutDown() {
		if(sessionFactory.isOpen())
				sessionFactory.close();
	}
}
