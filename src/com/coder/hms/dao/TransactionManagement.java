package com.coder.hms.dao;

import org.hibernate.Session;

public interface TransactionManagement {

	public void beginTransactionIfAllowed(Session theSession);
}
