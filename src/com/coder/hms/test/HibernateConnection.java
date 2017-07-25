/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.test;

import org.hibernate.Session;

import com.coder.hms.connection.DataSourceFactory;

public class HibernateConnection {

	public static void main(String[] args) {

			final DataSourceFactory cf = new DataSourceFactory();
			
			Session session = cf.getSessionFactory().getCurrentSession();
			
			
			session.beginTransaction();
			System.out.println(session.isConnected());
			session.close();

		
	}

}
