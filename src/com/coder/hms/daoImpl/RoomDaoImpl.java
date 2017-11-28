/**
 * @author Coder ACJHP
 * @Email hexa.octabin@gmail.com
 * @Date 15/07/2017
 */
package com.coder.hms.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.RoomDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Room;
import com.coder.hms.ui.external.InformationFrame;
import com.coder.hms.utils.LoggingEngine;

public class RoomDaoImpl implements RoomDAO, TransactionManagement {

    private Session session;
    private static LoggingEngine logging;
    private DataSourceFactory dataSourceFactory;

    public RoomDaoImpl() {

        dataSourceFactory = new DataSourceFactory();
        DataSourceFactory.createConnection();
        logging = LoggingEngine.getInstance();

    }

    @Override
    public Room getRoomByRoomNumber(String roomNumber) {

        try {
            
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Room> query = session.createQuery("from Room where number=:roomNumber", Room.class);
            query.setParameter("roomNumber", roomNumber);
            
            logging.setMessage("RoomDaoImpl -> fetching room by number "+roomNumber);
            return query.getSingleResult();

        } catch (NonUniqueResultException e) {
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
            final InformationFrame frame = new InformationFrame();
            frame.setMessage("There is more than one room with this number!");
            frame.setVisible(true);
        }
        session.close();
        return null;
    }

    @Override
    public void saveRoom(Room room) {
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            session.save(room);
            session.getTransaction().commit();
            logging.setMessage("RoomDaoImpl -> room saved successfully.");
            
        } catch (HibernateException e) {
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
            session.getTransaction().rollback();
        }
        session.close();
    }

    @Override
    public List<Room> getAllRooms() {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Room> query = session.createQuery("from Room", Room.class);
            logging.setMessage("RoomDaoImpl -> fetching all rooms...");
            return query.getResultList();

        } catch (HibernateException e) {
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
            session.getTransaction().rollback();
        }
        session.close();
        return null;
    }

    @Override
    public Room getRoomByReservId(long id) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<Room> query = session.createQuery("from Room where ReservationId=:id", Room.class);
            query.setParameter("id", id);
            logging.setMessage("RoomDaoImpl -> fetching room by identity :"+id);
            return query.getSingleResult();

        } catch (NonUniqueResultException e) {
            session.getTransaction().rollback();
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
            final InformationFrame frame = new InformationFrame();
            frame.setMessage(e.getLocalizedMessage());
            frame.setVisible(true);
        }
        session.close();
        return null;
    }

    @Override
    public void setAllRoomsAtClean(String clean) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus=:clean");
            query.setParameter("clean", clean);
            query.executeUpdate();
            
            logging.setMessage("RoomDaoImpl -> All rooms status updated to clean successfully.");
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
        }
        session.close();
    }

    @Override
    public void setSingleRoomAsCleanByRoomNumber(String rowData) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus = 'CLEAN' where number=:rowData");
            query.setParameter("rowData", rowData);
            query.executeUpdate();
            
            logging.setMessage("RoomDaoImpl -> room status updated to clean successfully.");
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
        }
        session.close();
    }

    @Override
    public void setRoomCheckedOut(String num) {
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            final String HQL = "UPDATE Room SET usageStatus = 'EMPTY', personCount = 0, price = 0, "
                    + "totalPrice = 0, balance = '0', customerGrupName = '', currency = '', remainingDebt = 0, ReservationId = 0 where number=:num";
            Query<?> query = session.createQuery(HQL);
            query.setParameter("num", num);
            query.executeUpdate();
            
            logging.setMessage("RoomDaoImpl -> room number :"+num+" checked out successfully.");
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
        }
        session.close();

    }

    @Override
    public void setAllRoomsAtDirty(String dirty) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus=:dirty");
            query.setParameter("dirty", dirty);
            query.executeUpdate();
            
            logging.setMessage("RoomDaoImpl -> all rooms status updated to dirty successfully.");
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
        }
        session.close();
    }

    @Override
    public void setSingleRoomAsDirtyByRoomNumber(String roomNumber) {
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus = 'DIRTY' where number=:roomNumber");
            query.setParameter("roomNumber", roomNumber);
            query.executeUpdate();
            
            logging.setMessage("RoomDaoImpl -> room number :"+roomNumber+" status updated to dirty successfully.");
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
        }
        session.close();
    }

    @Override
    public void setSingleRoomAsDNDByRoomNumber(String roomNumber) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            Query<?> query = session.createQuery("UPDATE Room SET cleaningStatus = 'DND' where number=:roomNumber");
            query.setParameter("roomNumber", roomNumber);
            query.executeUpdate();
            
            logging.setMessage("RoomDaoImpl -> room number :"+roomNumber+" status updated to dnd successfully.");
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
        }
        session.close();

    }

    @Override
    public void updateRoom(Room theRoom) {
        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);
            session.update(theRoom);
            session.getTransaction().commit();

            logging.setMessage("RoomDaoImpl -> room number :"+theRoom.getNumber()+" status updated successfully.");
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
        }
        session.close();
    }

    public void setRoomAsDefaultByRoomNumber(String theNumber) {

        try {
            session = dataSourceFactory.getSessionFactory().openSession();
            beginTransactionIfAllowed(session);

            Query<?> query = session .createQuery("UPDATE Room SET price = :price, totalPrice = :total,"
                    + " balance = :balance, cleaningStatus = :clnSts, usageStatus = :usgSts, "
                    + "personCount = :prsnCnt, customerGroupName = :groupName, ReservationId = :reservId, "
                    + "currency = :currency, remainingDebt = :debt WHERE number = :theNumber");
            
            query.setParameter("price", 0);
            query.setParameter("total", "0");
            query.setParameter("balance", "0");
            query.setParameter("clnSts", "CLEAN");
            query.setParameter("usgSts", "EMPTY");
            query.setParameter("prsnCnt", 0);
            query.setParameter("groupName", "");
            query.setParameter("reservId", 0);
            query.setParameter("currency", "TURKISH LIRA");
            query.setParameter("debt", 0);
            query.setParameter("theNumber", theNumber);
            query.executeUpdate();
            session.getTransaction().commit();

            logging.setMessage("RoomDaoImpl -> room number :"+theNumber+" status updated to default successfully.");
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logging.setMessage("RoomDaoImpl -> "+e.getLocalizedMessage());
        }
        session.close();
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
