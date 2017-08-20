package com.coder.hms.daoImpl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.HotelSystemStatusDAO;
import com.coder.hms.entities.HotelSystemStatus;

public class HotelSystemStatusImpl implements HotelSystemStatusDAO {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public HotelSystemStatusImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
	}
	
	@Override
	public HotelSystemStatus getSystemStatus() {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<HotelSystemStatus> query = session.createQuery("from HotelSystemStatus where id=1", HotelSystemStatus.class);
		HotelSystemStatus status = query.getSingleResult();
		
		session.close();
		return status;
	}

	@Override
	public void updateSystemStatus(HotelSystemStatus hotelSystemStatus) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		session.saveOrUpdate(hotelSystemStatus);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void beginTransactionIfAllowed(Session theSession) {
		if(!theSession.getTransaction().isActive()) {
			theSession.beginTransaction();	
		}else {
			theSession.getTransaction().rollback();
			theSession.beginTransaction();
		}
		
	}
}
