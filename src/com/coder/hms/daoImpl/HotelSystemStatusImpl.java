package com.coder.hms.daoImpl;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.HotelSystemStatusDAO;
import com.coder.hms.entities.HotelSystemStatus;
import com.coder.hms.utils.LoggingEngine;

public class HotelSystemStatusImpl implements HotelSystemStatusDAO {

	private Session session;
        private static LoggingEngine logging;
	private DataSourceFactory dataSourceFactory;
	
	public HotelSystemStatusImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
                logging = LoggingEngine.getInstance();
	}
	
	@Override
	public HotelSystemStatus getSystemStatus() {
		HotelSystemStatus systemStatus = null;
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<HotelSystemStatus> query = session.createQuery("from HotelSystemStatus where id=1", HotelSystemStatus.class);
			systemStatus = query.getSingleResult();
                        
			logging.setMessage("HotelSystemStatusImpl -> fetching system status...");
                        
		} catch (NoResultException e) {
			logging.setMessage("HotelSystemStatusImpl Error -> "+e.getLocalizedMessage());
		} finally {
			session.close();
		}
                return systemStatus;
	}

	@Override
	public void updateSystemStatus(HotelSystemStatus hotelSystemStatus) {
	
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.saveOrUpdate(hotelSystemStatus);
			session.getTransaction().commit();
			logging.setMessage("HotelSystemStatusImpl -> system status updated sucessfully.");
                        
		} catch (HibernateException e) {
			session.getTransaction().rollback();
                        logging.setMessage("HotelSystemStatusImpl Error -> "+e.getLocalizedMessage());
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
