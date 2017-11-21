package com.coder.hms.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.PostingDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Posting;

public class PostingDaoImpl implements PostingDAO, TransactionManagement {
	
	private Session session;
	private DataSourceFactory dataSourceFactory;

	public PostingDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
	}
	
	@Override
	public void savePosting(Posting posting) {
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.saveOrUpdate(posting);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	@Override
	public boolean deletePosting(long theId) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<?> query = session.createQuery("delete Posting where id = :theId");
			query.setParameter("theId", theId);
			query.executeUpdate();
			return true;
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public Posting getPostingById(long Id) {
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			return session.get(Posting.class, Id);
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<Posting> getAllPostingsByRoomNumber(String theRoomNumber, String string) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Posting> query = session.createQuery("from Posting where"
					+ " roomNumber = :theRoomNumber and dateTime >= :localDate", Posting.class);
			query.setParameter("theRoomNumber", theRoomNumber);
			query.setParameter("localDate", string);
			return query.getResultList();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}

	}


	public List<Posting> getAllPostingsForToday(String today) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Posting> query = session.createQuery("from Posting where dateTime >= :today", Posting.class);
			query.setParameter("today", today);
			return query.getResultList();

		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public String getTotalCashLiraPostingsForOneDay(String today) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery("select sum(price) from Posting where currency = 'TURKISH LIRA' and dateTime >= :today", String.class);
			query.setParameter("today", today);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}

	}

	@Override
	public String getTotalCashDollarPostingsForOneDay(String today) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery(
					"select sum(price) from Posting where currency = 'DOLLAR' and dateTime >= :today", String.class);
			query.setParameter("today", today);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public String getTotalCashEuroPostingsForOneDay(String today) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery("select sum(price) from Posting where currency = 'EURO' and dateTime >= :today", String.class);
			query.setParameter("today", today);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public String getTotalCashPoundPostingsForOneDay(String today) {

		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery(
					"select sum(price) from Posting where currency = 'POUND' and dateTime >= :today", String.class);
			query.setParameter("today", today);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}


	@Override
	public String getTotalCreditPostingsForOneDay(String date) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<String> query = session.createQuery("select sum(price) from Posting where "
					+ "currency = 'CREDIT CARD' and dateTime >= :date", String.class);
			query.setParameter("date", date);
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
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
