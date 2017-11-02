/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.connection;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import com.coder.hms.entities.Reservation;
import com.coder.hms.ui.external.InformationFrame;

public class DataSourceFactory {

	private static SessionFactory sessionFactory = null;

	public DataSourceFactory() {}

	public static synchronized void createConnection() {
		try {

			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("com/coder/hms/connection/hibernate.cfg.xml")
						.addAnnotatedClass(Reservation.class).buildSessionFactory();
			}

		} catch (HibernateException e) {
			Toolkit.getDefaultToolkit().beep();
			final InformationFrame dialog = new InformationFrame();
			dialog.setMessage("Sorry we can't connect to database right now, without "
					+ "connection the application will not work properly.");
			dialog.okBtn.addActionListener(ActionListener->{
				System.exit(1);
			});
			dialog.setVisible(true);
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
			final InformationFrame INFORMATION_FRAME = new InformationFrame();
			INFORMATION_FRAME.setMessage("Connection converting error!");
			INFORMATION_FRAME.setVisible(true);
		}
		return connection;
	}
	
	public Transaction getTransaction() {
		return getSessionFactory().getCurrentSession().beginTransaction();
	}
	
	public void shutDown() {
		if(sessionFactory != null && sessionFactory.isOpen())
				sessionFactory.close();
	}
}
