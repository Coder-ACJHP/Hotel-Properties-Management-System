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
import com.coder.hms.dao.ReservationDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Reservation;
import com.coder.hms.ui.external.InformationFrame;
import com.coder.hms.utils.LoggingEngine;

public class ReservationDaoImpl implements ReservationDAO, TransactionManagement {

    private Session session;
    private LoggingEngine logging;
    private DataSourceFactory dataSourceFactory;

    public ReservationDaoImpl() {

        dataSourceFactory = new DataSourceFactory();
        DataSourceFactory.createConnection();
        logging = LoggingEngine.getInstance();
    }

    @Override
    public Reservation findReservationById(long theId) {
        Reservation theReservation = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Reservation> query = session.createQuery("from Reservation where id=:theId", Reservation.class);
            query.setParameter("theId", theId);
            query.setMaxResults(1);
            theReservation = query.getSingleResult();

            logging.setMessage("ReservationDaoImpl -> fetching reservation by Id...");

        } catch (NoResultException e) {
            final InformationFrame frame = new InformationFrame();
            frame.setMessage("No reservation found!");
            frame.setVisible(true);
        } finally {
            session.close();
        }
        return theReservation;
    }

    @Override
    public Reservation findSingleReservByThisDate(String Date) {
        Reservation theReservation = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:Date", Reservation.class);
            query.setParameter("Date", Date);
            theReservation = query.getSingleResult();

            logging.setMessage("ReservationDaoImpl -> fetching reservation by date...");

        } catch (NoResultException e) {
            final InformationFrame frame = new InformationFrame();
            frame.setMessage("There is no reservation at this date!");
            frame.setVisible(true);
        } finally {
            session.close();
        }
        return theReservation;
    }

    @Override
    public List<Reservation> getReservListByThisDate(String today) {
        List<Reservation> reservationsList = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Reservation> query = session.createQuery("from Reservation where checkinDate=:today", Reservation.class);
            query.setParameter("today", today);
            reservationsList = query.getResultList();

            logging.setMessage("ReservationDaoImpl -> fetching all reservations by date...");

        } catch (NoResultException e) {
            final InformationFrame frame = new InformationFrame();
            frame.setMessage("No reservation found!");
            frame.setVisible(true);
        } finally {
            session.close();
        }
        return reservationsList;
    }

    @Override
    public void saveReservation(Reservation reservation) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            session.save(reservation);
            session.getTransaction().commit();

            logging.setMessage("ReservationDaoImpl -> reservations saved successfully.");

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("ReservationDaoImpl Error -> " + e.getLocalizedMessage());
        }
        session.close();

    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservationsList = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Reservation> query = session.createQuery("from Reservation", Reservation.class);
            reservationsList = query.getResultList();

            logging.setMessage("ReservationDaoImpl -> fetching all reservations...");

        } catch (NoResultException e) {
            logging.setMessage("ReservationDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return reservationsList;
    }

    public List<Reservation> getGaranteedReservs(String reservDate) {
        List<Reservation> reservationsList = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Reservation> query = session.createQuery("from Reservation "
                    + "where bookStatus = 'GUARANTEE' and checkinDate=:today", Reservation.class);
            query.setParameter("today", reservDate);
            reservationsList = query.getResultList();

            logging.setMessage("ReservationDaoImpl -> fetching all garanteed reservations...");

        } catch (NoResultException e) {
            logging.setMessage("ReservationDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return reservationsList;
    }

    @Override
    public List<Reservation> getReservsAsWaitlist(String reservDate) {
        List<Reservation> reservationsList = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Reservation> query = session.createQuery("from Reservation "
                    + "where bookStatus = 'WAITLIST' and checkinDate=:today", Reservation.class);
            query.setParameter("today", reservDate);
            reservationsList = query.getResultList();

            logging.setMessage("ReservationDaoImpl -> fetching all waiting reservations...");

        } catch (NoResultException e) {
            logging.setMessage("ReservationDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return reservationsList;
    }

    @Override
    public Reservation getLastReservation() {
        Reservation lastReservation = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Reservation> query = session.createQuery("from Reservation order by Id DESC", Reservation.class);
            query.setMaxResults(1);
            lastReservation = query.getSingleResult();

            logging.setMessage("ReservationDaoImpl -> fetching last reservation...");

        } catch (NoResultException e) {
            logging.setMessage("ReservationDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return lastReservation;
    }

    @Override
    public boolean updateReservation(Reservation reservation) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            session.update(reservation);
            session.getTransaction().commit();

            logging.setMessage("ReservationDaoImpl -> reservation updated successfully...");

            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("ReservationDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Reservation findReservationByAgencyRefNo(String agencyRefNo) {
        Reservation reservationByRef = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Reservation> query = session.createQuery("from Reservation where agencyRefNo=:agencyRefNo", Reservation.class);
            query.setParameter("agencyRefNo", agencyRefNo);
            query.setMaxResults(1);
            reservationByRef = query.getSingleResult();

            logging.setMessage("ReservationDaoImpl -> fetching reservation by agency referance number...");

        } catch (NoResultException e) {
            final InformationFrame frame = new InformationFrame();
            frame.setMessage("There is no reservation with this agency referance number!");
            frame.setVisible(true);
        } finally {
            session.close();
        }
        return reservationByRef;
    }

    @Override
    public Reservation findReservationByRefNo(String refNo) {
        Reservation reservationByRef = null;
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Reservation> query = session.createQuery("from Reservation where referanceNo=:refNo", Reservation.class);
            query.setParameter("refNo", refNo);
            query.setMaxResults(1);
            reservationByRef = query.getSingleResult();

            logging.setMessage("ReservationDaoImpl -> fetching reservation by referance number...");

        } catch (NoResultException e) {
            final InformationFrame frame = new InformationFrame();
            frame.setMessage("There is no reservation with this referance number!");
            frame.setVisible(true);
        } finally {
            session.close();
        }
        return reservationByRef;

    }

    @Override
    public void deleteReservation(long id) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Reservation res = session.load(Reservation.class, id);
            session.delete(res);
            session.flush();
            session.getTransaction().commit();

            logging.setMessage("ReservationDaoImpl -> reservation deleted successfully.");

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("ReservationDaoImpl Error -> " + e.getLocalizedMessage());
        } finally {
            session.close();
        }
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
