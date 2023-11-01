package com.coder.hms.daoImpl;

import com.coder.hms.dao.TransactionManagement;

public class SessionImpl implements TransactionManagement {
    @Override
    public void beginTransactionIfAllowed(org.hibernate.Session theSession) {
        if(!theSession.getTransaction().isActive()) {
            theSession.beginTransaction();
        }else {
            theSession.getTransaction().rollback();
            theSession.beginTransaction();
        }
    }


}
