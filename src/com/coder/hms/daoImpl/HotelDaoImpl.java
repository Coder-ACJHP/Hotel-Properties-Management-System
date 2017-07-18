package com.coder.hms.daoImpl;

import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.HotelDAO;
import com.coder.hms.entities.Hotel;

public class HotelDaoImpl implements HotelDAO {

	private Session session;
	private Transaction transaction;
	private DataSourceFactory dataSourceFactory;
	private final Logger LOGGER = Logger.getLogger(HotelDaoImpl.class.getName());
	
	public HotelDaoImpl() {
		dataSourceFactory = new DataSourceFactory();
		session = dataSourceFactory.getSession();
		transaction = session.beginTransaction();
	}
	
	@Override
	public void saveHotel(Hotel hotel) {
		
		session.saveOrUpdate(hotel);
		transaction.commit();
		
		LOGGER.info("Hotel " + hotel.toString()+"\nSaved successfully.");
	}

	@Override
	public Hotel getHotel() {
		
		Query<Hotel> query = session.createQuery("from Hotel", Hotel.class);
		Hotel hotel = query.getSingleResult();
		
		LOGGER.info("Getting "+ hotel.getName() + "details..");
		return hotel;
	}

}
