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

import com.coder.hms.entities.Company;
import com.coder.hms.entities.Customer;
import com.coder.hms.entities.Hotel;
import com.coder.hms.entities.HotelSystemStatus;
import com.coder.hms.entities.Payment;
import com.coder.hms.entities.Posting;
import com.coder.hms.entities.Reservation;
import com.coder.hms.entities.Room;
import com.coder.hms.entities.User;
import com.coder.hms.ui.external.InformationFrame;

public class DataSourceFactory {

	private static SessionFactory sessionFactory = null;

	public DataSourceFactory() {}

	public static void createConnection() {
		try {

				sessionFactory = new Configuration().configure("com/coder/hms/connection/hibernate.cfg.xml")
						.addAnnotatedClass(Reservation.class)
						.addAnnotatedClass(Company.class)
						.addAnnotatedClass(Customer.class)
						.addAnnotatedClass(Hotel.class)
						.addAnnotatedClass(HotelSystemStatus.class)
						.addAnnotatedClass(Payment.class)
						.addAnnotatedClass(Posting.class)
						.addAnnotatedClass(Room.class)
						.addAnnotatedClass(User.class)
						.buildSessionFactory();

		} catch (HibernateException e) {
			Toolkit.getDefaultToolkit().beep();
			final InformationFrame dialog = new InformationFrame();
			dialog.setMessage("Sorry we can't connect to database right now, without "
					+ "connection the application will not work properly.");
			dialog.okBtn.addActionListener(ActionListener->{
				return;
			});
			dialog.setVisible(true);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

        //replacement connection for old types like java.sql.Connection.
	public Connection getSqlConnection() {
		Connection connection = null;
		try {
			connection = getSessionFactory().
					getSessionFactoryOptions().getServiceRegistry().
					getService(ConnectionProvider.class).getConnection();
		} catch (SQLException e) {
			final InformationFrame INFORMATION_FRAME = new InformationFrame();
			INFORMATION_FRAME.setMessage("Connection converting error!\n" +e.getLocalizedMessage());
			INFORMATION_FRAME.setVisible(true);
		}
		return connection;
	}
	
	public Transaction getTransaction() {
		return getSessionFactory().openSession().beginTransaction();
	}
	
	public void shutDown() {
		if(sessionFactory != null && sessionFactory.isOpen())
                        sessionFactory.close();
	}
}
