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
import com.coder.hms.utils.EncryptPassword;
import com.coder.hms.utils.LoggingEngine;

public class UserDaoImpl implements UserDAO, TransactionManagement {

    private Session session;
    private static LoggingEngine logging;
    private final DataSourceFactory dataSourceFactory;
    private final EncryptPassword passwordEncrypter;

    public UserDaoImpl() {

        dataSourceFactory = new DataSourceFactory();
        DataSourceFactory.createConnection();
        passwordEncrypter = new EncryptPassword();
        logging = LoggingEngine.getInstance();
    }

    @Override
    public User getUserByName(String theName) {
        User user = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<User> query = session.createQuery("from User where NickName=:theName", User.class);
            query.setParameter("theName", theName);
            user = query.getSingleResult();
            
            logging.setMessage("UserDaoImpl -> user "+user.getNickName()+" saved successfully.");
            
        } catch (NoResultException e) {
            session.getTransaction().rollback();
            logging.setMessage("UserDaoImpl : " + e.getLocalizedMessage());            
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void saveUser(User user) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            
            //encrypt password before saving.
            user.setPassword(passwordEncrypter.encryptPassword(user.getPassword()));
            
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            logging.setMessage("UserDaoImpl -> user "+user.getNickName()+" saved successfully.");
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("UserDaoImpl : " + e.getLocalizedMessage());
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

            theUser.setPassword(passwordEncrypter.encryptPassword(newPassword));
            session.saveOrUpdate(theUser);
            session.getTransaction().commit();
            logging.setMessage("UserDaoImpl -> user "+nickName+" password updated successfully");
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("UserDaoImpl Error : " + e.getLocalizedMessage());
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
            Query<User> query = session.createQuery("from User where NickName=:userName", User.class);
            query.setParameter("userName", userName);
            final User theUser = query.getSingleResult();
            
            logging.setMessage("UserDaoImpl : checking credentials...");
            return passwordEncrypter.passwordIsMatch(userPswrd, theUser.getPassword());
            
        } catch (NoResultException e) {
            logging.setMessage("UserDaoImpl Error : " + e.getLocalizedMessage());
            return false;
        } finally {
            session.close();
        }

    }

    public User getUserByEmail(String theEmail) {
        User theUser = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<User> query = session.createQuery("from User where Email=:theEmail", User.class);
            query.setParameter("theEmail", theEmail);
            
            theUser =  query.getSingleResult();
            logging.setMessage("UserDaoImpl : fetching user who owner of "+theEmail);
            
        } catch (NoResultException e) {
            logging.setMessage("UserDaoImpl Error : " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return theUser;
    }

    @Override
    public void beginTransactionIfAllowed(Session theSession) {
        if (!theSession.getTransaction().isActive()) {
            theSession.beginTransaction();
        } else {
            theSession.getTransaction().rollback();
            theSession.beginTransaction();
        }

    }

}
