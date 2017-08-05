/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.HotelDAO;
import com.coder.hms.entities.Hotel;

public class HotelDaoImpl implements HotelDAO {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public HotelDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();

	}
	
	@Override
	public void saveHotel(Hotel hotel) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(hotel);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public Hotel getHotel() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Hotel> query = session.createQuery("from Hotel", Hotel.class);
		Hotel hotel = query.getSingleResult();
		session.close();
				
		return hotel;
	}

}
