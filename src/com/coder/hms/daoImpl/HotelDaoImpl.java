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
		session = dataSourceFactory.getSession();

	}
	
	@Override
	public void saveHotel(Hotel hotel) {
		
		session.saveOrUpdate(hotel);
		session.getTransaction().commit();
		
	}

	@Override
	public Hotel getHotel() {
		
		Query<Hotel> query = session.createQuery("from Hotel", Hotel.class);
		Hotel hotel = query.getSingleResult();
		
		return hotel;
	}

}
