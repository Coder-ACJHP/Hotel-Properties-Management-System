/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.UserDAO;
import com.coder.hms.entities.User;

public class UserDaoImpl implements UserDAO {

	private Session session;
	private DataSourceFactory dataSourceFactory;
	
	public UserDaoImpl() {
		
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
		
	}
	
	@Override
	public User getUserByName(String theName) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<User> query = session.createQuery("from User where NickName=:theName", User.class);
		query.setParameter("theName", theName);
		User user = query.getSingleResult();
		session.close();

		return user;
	}

	@Override
	public void saveUser(User user) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void changePasswordOfUser(String nickName, String newPassword) {
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<User> query = session.createQuery("from User where NickName=:nickName", User.class);
		query.setParameter("nickName", nickName);
		User theUser = query.getSingleResult();
		
		theUser.setPassword(newPassword);
		session.saveOrUpdate(theUser);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public List<User> getAllusers() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean authentication(String userName, String userPswrd) {
		boolean isUser = false;
		session = dataSourceFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query<User> query = session.createQuery("from User where NickName=:userName and Password=:userPswrd", User.class);
		query.setParameter("userName", userName);
		query.setParameter("userPswrd", userPswrd);
		User user = query.getSingleResult();
		
		session.close();
		
		if(user != null) {
			isUser = true;
		}
		
		return isUser;
	}

}
