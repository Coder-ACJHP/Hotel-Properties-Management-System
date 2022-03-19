/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import com.coder.hms.dao.SaveDao;
import com.coder.hms.entities.Company;
import com.coder.hms.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.entities.Customer;
import com.coder.hms.utils.LoggingEngine;

public class SaveDaoImpl implements SaveDao {

    private Session session;
    private static LoggingEngine logging;
    private DataSourceFactory dataSourceFactory;

    public SaveDaoImpl() {

        dataSourceFactory = new DataSourceFactory();
        DataSourceFactory.createConnection();
        logging = LoggingEngine.getInstance();

    }

    @Override
    public boolean save(String type, Object object) {
        try {
            if(type.equalsIgnoreCase("user"))
                object = (User) object;
            if(type.equalsIgnoreCase("customer"))
                object = (Customer) object;
            if(type.equalsIgnoreCase("company"))
                object = (Company) object;

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransaction(session);
            session.saveOrUpdate(object);
            session.getTransaction().commit();

            logging.setMessage("SaveDaoImpl ->  saved successfully");
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("SaveDaoImpl -> save error -> "+e.getLocalizedMessage());
            return false;
        } finally {
            session.close();
        }
    }

    public void beginTransaction(Session theSession)
    {
        SessionImpl sessionImpl = new SessionImpl();
        sessionImpl.beginTransactionIfAllowed(theSession);
    }
}
