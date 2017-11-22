/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.HotelDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Hotel;

public class HotelDaoImpl implements HotelDAO, TransactionManagement {

    private Session session;
    private DataSourceFactory dataSourceFactory;

    public HotelDaoImpl() {

        dataSourceFactory = new DataSourceFactory();
        DataSourceFactory.createConnection();

    }

    @Override
    public void saveHotel(Hotel hotel) {

        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            session.saveOrUpdate(hotel);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public Hotel getHotel() {
        Hotel hotel = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Hotel> query = session.createQuery("from Hotel", Hotel.class);
            hotel = query.getSingleResult();

        } catch (NoResultException e) {
            e.getLocalizedMessage();
        } finally {
            session.close();
        }
        return hotel;
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
