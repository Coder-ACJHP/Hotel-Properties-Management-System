/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.dao.UserDAO;
import com.coder.hms.entities.User;

public class UserDaoImpl implements UserDAO, TransactionManagement {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public UserDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
		
	}
	
	@Override
	public User getUserByName(String theName) {
		
		try {
			
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<User> query = session.createQuery("from User where NickName=:theName", User.class);
			query.setParameter("theName", theName);
			return query.getSingleResult();

		}catch (NoResultException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
		
	}

	@Override
	public void saveUser(User user) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		session.close();
	}

	@Override
	public void changePasswordOfUser(String nickName, String newPassword) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<User> query = session.createQuery("from User where NickName=:nickName", User.class);
			query.setParameter("nickName", nickName);
			User theUser = query.getSingleResult();
			
			theUser.setPassword(newPassword);
			session.saveOrUpdate(theUser);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}
		session.close();
		
	}

	@Override
	public List<User> getAllusers() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean authentication(String userName, String userPswrd) {

		try {
			//here we cannot close the session because session is opening here
			//if login failed it will resume on same session.
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<User> query = session.createQuery("from User where NickName=:userName and Password=:userPswrd", User.class);
			query.setParameter("userName", userName);
			query.setParameter("userPswrd", userPswrd);
			query.getSingleResult();
			
			return true;
		} catch (NoResultException e) {
			return false;
		} finally {
			session.close();
		}
			
	}

	public User getUserByEmail(String theEmail) {
		
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<User> query = session.createQuery("from User where Email=:theEmail", User.class);
			query.setParameter("theEmail", theEmail);
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
