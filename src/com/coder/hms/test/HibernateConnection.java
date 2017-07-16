package com.coder.hms.test;

import org.hibernate.Session;

import com.coder.hms.connection.DataSourceFactory;

public class HibernateConnection {

	public static void main(String[] args) {

			final DataSourceFactory cf = new DataSourceFactory();
			
			Session session = cf.getSessionFactory().getCurrentSession();
			
			session.beginTransaction();
			System.out.println(session.isConnected());

		
	}

}
