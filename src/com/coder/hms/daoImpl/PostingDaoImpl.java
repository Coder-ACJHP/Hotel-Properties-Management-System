package com.coder.hms.daoImpl;

import java.util.List;

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
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		session.saveOrUpdate(posting);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public boolean deletePosting(long theId) {
		boolean result = false;
		
		try {
			session = dataSourceFactory.getSessionFactory().getCurrentSession();
			beginTransactionIfAllowed(session);
			Query<?> query = session.createQuery("delete Posting where id = :theId");
			query.setParameter("theId", theId);
			query.executeUpdate();
			session.close();
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public Posting getPostingById(long Id) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		final Posting posting = session.get(Posting.class, Id);
		session.close();
		
		return posting;
	}

	@Override
	public List<Posting> getAllPostingsByRoomNumber(String theRoomNumber, String string) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Posting> query = session.createQuery("from Posting where"
				+ " roomNumber = :theRoomNumber and dateTime >= :localDate", Posting.class);
		query.setParameter("theRoomNumber", theRoomNumber);
		query.setParameter("localDate", string);
		List<Posting> postList = query.getResultList();
		session.close();
		
		return postList;
	}


	public List<Posting> getAllPostingsForToday(String today) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<Posting> query = session.createQuery("from Posting where dateTime >= :today", Posting.class);
		query.setParameter("today", today);
		List<Posting> postList = query.getResultList();
		session.close();
		
		return postList;
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

	@Override
	public String getTotalCashLiraPostingsForOneDay(String today) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Posting where currency = 'TURKISH LIRA' and dateTime >= :today", String.class);
		query.setParameter("today", today);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalCashDollarPostingsForOneDay(String today) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Posting where currency = 'DOLLAR' and dateTime >= :today", String.class);
		query.setParameter("today", today);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalCashEuroPostingsForOneDay(String today) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Posting where currency = 'EURO' and dateTime >= :today", String.class);
		query.setParameter("today", today);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

	@Override
	public String getTotalCashPoundPostingsForOneDay(String today) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Posting where currency = 'POUND' and dateTime >= :today", String.class);
		query.setParameter("today", today);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}


	@Override
	public String getTotalCreditPostingsForOneDay(String date) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		beginTransactionIfAllowed(session);
		Query<String> query = session.createQuery("select sum(price) from Posting where "
				+ "currency = 'CREDIT CARD' and dateTime >= :date", String.class);
		query.setParameter("date", date);
		final String totalVal = query.getSingleResult();
		session.close();
		return totalVal;
	}

}
