package com.coder.hms.daoImpl;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
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
		
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<HotelSystemStatus> query = session.createQuery("from HotelSystemStatus where id=1", HotelSystemStatus.class);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public void updateSystemStatus(HotelSystemStatus hotelSystemStatus) {
	
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.saveOrUpdate(hotelSystemStatus);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

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
