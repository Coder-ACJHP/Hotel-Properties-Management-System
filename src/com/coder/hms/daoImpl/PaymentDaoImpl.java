package com.coder.hms.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.PaymentDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Payment;
import com.coder.hms.utils.LoggingEngine;

public class PaymentDaoImpl implements PaymentDAO, TransactionManagement {

    private Session session;
    private LoggingEngine logging;
    private DataSourceFactory dataSourceFactory;

    public PaymentDaoImpl() {

        dataSourceFactory = new DataSourceFactory();
        DataSourceFactory.createConnection();
        logging = LoggingEngine.getInstance();
    }

    @Override
    public void savePayment(Payment payment) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            session.saveOrUpdate(payment);
            session.getTransaction().commit();
            logging.setMessage("PaymentDaoImpl -> payment saved successfully.");

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }

    }

    @Override
    public boolean deletePayment(long theId) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Payment payment = session.get(Payment.class, theId);
            session.delete(payment);
            session.getTransaction().commit();
            logging.setMessage("PaymentDaoImpl -> payment deleted successfully.");
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Payment getPaymentById(long Id) {
        Payment thePayment = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            thePayment = session.get(Payment.class, Id);
            logging.setMessage("PaymentDaoImpl -> fetching payment : " + thePayment.toString());

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return thePayment;
    }

    @Override
    public List<Payment> getAllPaymentsByRoomNumber(String theRoomNumber, String string) {
        List<Payment> paymentsList = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Payment> query = session.createQuery("from Payment where "
                    + "roomNumber = :theRoomNumber and dateTime >= :localDate", Payment.class);
            query.setParameter("theRoomNumber", theRoomNumber);
            query.setParameter("localDate", string);
            paymentsList = query.getResultList();
            logging.setMessage("PaymentDaoImpl -> fetching all payments...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return paymentsList;
    }

    @Override
    public String getTotalCashDollarForOneDay(String date) {
        String totalCash = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
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
            beginTransactionIfAllowed(session);
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
            beginTransactionIfAllowed(session);
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
            beginTransactionIfAllowed(session);
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
    public String getTotalCreditPaymentsForOneDay(String date) {
        String totalCredit = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
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

    public List<Payment> getAllPaymentsForToday(String today) {
        List<Payment> paymentsList = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Payment> query = session.createQuery("from Payment where dateTime >= :today", Payment.class);
            query.setParameter("today", today);
            paymentsList = query.getResultList();

            logging.setMessage("PaymentDaoImpl -> fetching all payments for today...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return paymentsList;
    }

    @Override
    public Payment getLastPayment() {
        Payment thePayment = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Payment> query = session.createQuery("from Payment order by Id DESC", Payment.class);
            query.setMaxResults(1);
            thePayment = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching last payment for today...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return thePayment;
    }

    @Override
    public Payment getEarlyPaymentByRoomNumber(String number) {
        Payment thePayment = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Payment> query = session.createQuery(
                    "from Payment where roomNumber = :theRoomNumber and title = 'EARLY PAYMENT'", Payment.class);
            query.setParameter("theRoomNumber", number);
            query.setMaxResults(1);
            thePayment = query.getSingleResult();

            logging.setMessage("PaymentDaoImpl -> fetching early payment by room number...");

        } catch (NoResultException e) {
            logging.setMessage("PaymentDaoImpl Error ->" + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return thePayment;
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
