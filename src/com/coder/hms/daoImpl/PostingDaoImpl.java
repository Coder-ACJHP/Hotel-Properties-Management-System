package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.PostingDAO;
import com.coder.hms.entities.Posting;

public class PostingDaoImpl implements PostingDAO {
	
	private Session session;
	private DataSourceFactory dataSourceFactory;

	public PostingDaoImpl() {
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
	}
	
	@Override
	public void savePosting(Posting posting) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(posting);
		session.getTransaction().commit();
		session.close();

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deletePosting(long theId) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("delete Posting where id = :theId");
		query.setParameter("theId", theId);
		query.executeUpdate();
		session.close();

	}

	@Override
	public Posting getPostingById(long Id) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Posting posting = session.get(Posting.class, Id);
		session.close();
		return posting;
	}

	@Override
	public List<Posting> getAllPostingsByRoomNumber(String theRoomNumber) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<Posting> query = session.createQuery("from Posting where roomNumber = :theRoomNumber", Posting.class);
		query.setParameter("theRoomNumber", theRoomNumber);
		List<Posting> postList = query.getResultList();
		session.close();
		return postList;
	}

}
