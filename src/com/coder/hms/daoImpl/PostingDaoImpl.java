package com.coder.hms.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.PostingDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Posting;
import com.coder.hms.utils.LoggingEngine;

public class PostingDaoImpl implements PostingDAO, TransactionManagement {

    private Session session;
    private LoggingEngine logging;
    private DataSourceFactory dataSourceFactory;

    public PostingDaoImpl() {

        dataSourceFactory = new DataSourceFactory();
        DataSourceFactory.createConnection();
        logging = LoggingEngine.getInstance();
    }

    @Override
    public void savePosting(Posting posting) {
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            session.saveOrUpdate(posting);
            session.getTransaction().commit();
            logging.setMessage("PostingDaoImpl -> payment saved successfully.");

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deletePosting(long theId) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<?> query = session.createQuery("delete Posting where id = :theId");
            query.setParameter("theId", theId);
            query.executeUpdate();

            logging.setMessage("PostingDaoImpl -> posting deleted successfully.");
            return true;

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Posting getPostingById(long Id) {
        Posting thePosting = null;
        try {

            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            thePosting = session.get(Posting.class, Id);
            logging.setMessage("PostingDaoImpl -> fetching posting...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return thePosting;
    }

    @Override
    public List<Posting> getAllPostingsByRoomNumber(String theRoomNumber, String string) {
        List<Posting> postingsList = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Posting> query = session.createQuery("from Posting where"
                    + " roomNumber = :theRoomNumber and dateTime >= :localDate", Posting.class);
            query.setParameter("theRoomNumber", theRoomNumber);
            query.setParameter("localDate", string);
            postingsList = query.getResultList();

            logging.setMessage("PostingDaoImpl -> fetching all postings by room number...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return postingsList;
    }

    public List<Posting> getAllPostingsForToday(String today) {
        List<Posting> postingsList = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Posting> query = session.createQuery("from Posting where dateTime >= :today", Posting.class);
            query.setParameter("today", today);
            postingsList = query.getResultList();

            logging.setMessage("PostingDaoImpl -> fetching all postings for today...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return postingsList;
    }

    @Override
    public String getTotalCashLiraPostingsForOneDay(String today) {
        String totalCash = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<String> query = session.createQuery("select sum(price) from Posting where currency = 'TURKISH LIRA' and dateTime >= :today", String.class);
            query.setParameter("today", today);
            totalCash = query.getSingleResult();

            logging.setMessage("PostingDaoImpl -> fetching total cash lira posting for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCash;
    }

    @Override
    public String getTotalCashDollarPostingsForOneDay(String today) {
        String totalCash = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<String> query = session.createQuery(
                    "select sum(price) from Posting where currency = 'DOLLAR' and dateTime >= :today", String.class);
            query.setParameter("today", today);
            totalCash = query.getSingleResult();

            logging.setMessage("PostingDaoImpl -> fetching total cash dollar posting for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCash;
    }

    @Override
    public String getTotalCashEuroPostingsForOneDay(String today) {
        String totalCash = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<String> query = session.createQuery("select sum(price) from Posting where currency = 'EURO' and dateTime >= :today", String.class);
            query.setParameter("today", today);
            totalCash = query.getSingleResult();

            logging.setMessage("PostingDaoImpl -> fetching total cash dollar posting for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCash;
    }

    @Override
    public String getTotalCashPoundPostingsForOneDay(String today) {
        String totalCash = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<String> query = session.createQuery(
                    "select sum(price) from Posting where currency = 'POUND' and dateTime >= :today", String.class);
            query.setParameter("today", today);
            totalCash = query.getSingleResult();
            logging.setMessage("PostingDaoImpl -> fetching total cash dollar posting for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCash;
    }

    @Override
    public String getTotalCreditLiraPostingsForOneDay(String date) {
        String totalCredit = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<String> query = session.createQuery("select sum(price) from Posting where "
                    + "currency = 'CREDIT CARD' and currency = 'TURKISH LIRA' and dateTime >= :date", String.class);
            query.setParameter("date", date);
            totalCredit = query.getSingleResult();

            logging.setMessage("PostingDaoImpl -> fetching total credit card lira posting for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCredit;
    }

    @Override
    public String getTotalCreditDollarPostingsForOneDay(String date) {
        String totalCredit = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<String> query = session.createQuery("select sum(price) from Posting where "
                    + "currency = 'CREDIT CARD' and currency = 'DOLLAR' and dateTime >= :date", String.class);
            query.setParameter("date", date);
            totalCredit = query.getSingleResult();

            logging.setMessage("PostingDaoImpl -> fetching total credit card dollar posting for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCredit;
    }

    @Override
    public String getTotalCreditEuroPostingsForOneDay(String date) {
    String totalCredit = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<String> query = session.createQuery("select sum(price) from Posting where "
                    + "currency = 'CREDIT CARD' and currency = 'EURO' and dateTime >= :date", String.class);
            query.setParameter("date", date);
            totalCredit = query.getSingleResult();

            logging.setMessage("PostingDaoImpl -> fetching total credit card euro posting for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCredit;
    }

    @Override
    public String getTotalCreditPoundPostingsForOneDay(String date) {
        String totalCredit = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<String> query = session.createQuery("select sum(price) from Posting where "
                    + "currency = 'CREDIT CARD' and currency = 'POUND' and dateTime >= :date", String.class);
            query.setParameter("date", date);
            totalCredit = query.getSingleResult();

            logging.setMessage("PostingDaoImpl -> fetching total credit card pound posting for one day...");

        } catch (NoResultException e) {
            logging.setMessage("PostingDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return totalCredit;
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
