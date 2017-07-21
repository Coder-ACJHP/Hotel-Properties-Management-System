package com.coder.hms.daoImpl;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.UserDAO;
import com.coder.hms.entities.User;

public class UserDaoImpl implements UserDAO {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	private final Logger LOGGER = Logger.getLogger(HotelDaoImpl.class.getName());
	
	public UserDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
		session = dataSourceFactory.getSession();
		
	}
	
	@Override
	public User getUserByName(String theName) {
		Query<User> query = session.createQuery("from User where NickName=:theName", User.class);
		query.setParameter("theName", theName);
		User user = query.getSingleResult();
		
		LOGGER.info("Returning user : " + user);
		
		return user;
	}

	@Override
	public void saveUser(User user) {
		session.saveOrUpdate(user);
		session.getTransaction().commit();

		LOGGER.info("User : " + user + " saved successfully.");
	}

	@Override
	public void changePasswordOfUser(String nickName, String newPassword) {
		Query<User> query = session.createQuery("from User where NickName=:nickName", User.class);
		query.setParameter("nickName", nickName);
		User theUser = query.getSingleResult();
		
		theUser.setPassword(newPassword);
		session.saveOrUpdate(theUser);
		session.getTransaction().commit();
		
		LOGGER.info("User password updated successfully.");
	}

	@Override
	public List<User> getAllusers() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean authentication(String userName, String userPswrd) {
		boolean isUser = false;
		Query<User> query = session.createQuery("from User where NickName=:userName and Password=:userPswrd", User.class);
		query.setParameter("userName", userName);
		query.setParameter("userPswrd", userPswrd);
		User user = query.getSingleResult();
		
		
		if(user != null) {
			isUser = true;
		}
		return isUser;
	}

}
