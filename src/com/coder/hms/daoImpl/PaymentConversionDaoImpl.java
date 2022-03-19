package com.coder.hms.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.PaymentConversionDao;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Payment;
import com.coder.hms.utils.LoggingEngine;

public class PaymentConversionDaoImpl implements PaymentConversionDao {

    private Session session;
    private LoggingEngine logging;
    private DataSourceFactory dataSourceFactory;

    public PaymentConversionDaoImpl() {

        dataSourceFactory = new DataSourceFactory();
        DataSourceFactory.createConnection();
        logging = LoggingEngine.getInstance();
    }

    @Override
    public String getTotalCashDollarForOneDay(String date) {
        String totalCash = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransaction(session);
            Query<String> query = session.createQuery("select sum(price) from Payment where "
                    + "paymentType = 'CASH PAYMENT' and currency = 'DOLLAR' and dateTime >= :date", String.class);
            query.setParameter("date", date);
            totalCash = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching total cash dollar for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCash;
    }

    @Override
    public String getTotalCashLiraPaymentsForOneDay(String date) {
        String totalCash = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransaction(session);
            Query<String> query = session.createQuery("select sum(price) from Payment where "
                    + "paymentType = 'CASH PAYMENT' and currency = 'TURKISH LIRA' and dateTime >= :date", String.class);
            query.setParameter("date", date);
            totalCash = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching total cash lira for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCash;
    }

    @Override
    public String getTotalCashEuroPaymentsForOneDay(String date) {
        String totalCash = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransaction(session);
            Query<String> query = session.createQuery("select sum(price) from Payment where "
                    + "paymentType = 'CASH PAYMENT' and currency = 'EURO' and dateTime >= :date", String.class);
            query.setParameter("date", date);
            totalCash = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching total cash euro for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCash;
    }

    @Override
    public String getTotalCashPoundPaymentsForOneDay(String date) {
        String totalCash = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransaction(session);
            Query<String> query = session.createQuery("select sum(price) from Payment where "
                    + " paymentType = 'CASH PAYMENT' and currency = 'POUND' and dateTime >= :date", String.class);
            query.setParameter("date", date);
            totalCash = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching total cash pound for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCash;
    }

    @Override
    public String getTotalCreditLiraPaymentsForOneDay(String date) {
        String totalCredit = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransaction(session);
            Query<String> query = session.createQuery(
                    "select sum(price) from Payment where "
                            + "paymentType = 'CREDIT CARD' and currency = 'TURKISH LIRA' and dateTime >= :date",
                    String.class);
            query.setParameter("date", date);
            totalCredit = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching total credit pound for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCredit;
    }


    @Override
    public String getTotalCreditDollarPaymentsForOneDay(String date) {
        String totalCredit = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransaction(session);
            Query<String> query = session.createQuery(
                    "select sum(price) from Payment where "
                            + "paymentType = 'CREDIT CARD' and currency = 'DOLLAR' and dateTime >= :date",
                    String.class);
            query.setParameter("date", date);
            totalCredit = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching total credit dollar for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCredit;
    }

    @Override
    public String getTotalCreditEuroPaymentsForOneDay(String date) {
        String totalCredit = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransaction(session);
            Query<String> query = session.createQuery(
                    "select sum(price) from Payment where "
                            + "paymentType = 'CREDIT CARD' and currency = 'EURO' and dateTime >= :date",
                    String.class);
            query.setParameter("date", date);
            totalCredit = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching total credit euro for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCredit;
    }

    @Override
    public String getTotalCreditPoundPaymentsForOneDay(String date) {
        String totalCredit = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransaction(session);
            Query<String> query = session.createQuery(
                    "select sum(price) from Payment where "
                            + "paymentType = 'CREDIT CARD' and currency = 'POUND' and dateTime >= :date",
                    String.class);
            query.setParameter("date", date);
            totalCredit = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching total credit pound for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCredit;
    }
    
    public void beginTransaction(Session theSession)
    {
        SessionImpl sessionImpl = new SessionImpl();
        sessionImpl.beginTransactionIfAllowed(theSession);
    }
}
