package com.coder.hms.daoImpl;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.HotelDAO;
import com.coder.hms.entities.Hotel;

public class HotelDaoImpl implements HotelDAO {

	private DataSourceFactory dataSourceFactory;
	private SessionFactory sessionFactory;
	private Session session;
	
	public HotelDaoImpl() {
		dataSourceFactory = new DataSourceFactory();
		sessionFactory = dataSourceFactory.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	}
	
	@Override
	public void saveHotel(Hotel hotel) {
		// TODO Auto-generated method stub

	}

	@Override
	public Hotel getHotel() {
		
		Query<Hotel> query = session.createQuery("from Hotel", Hotel.class);
		Hotel hotel = query.getSingleResult();
		return hotel;
	}

}
