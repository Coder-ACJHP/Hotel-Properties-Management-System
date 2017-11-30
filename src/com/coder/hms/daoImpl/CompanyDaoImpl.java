package com.coder.hms.daoImpl;


import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.coder.hms.connection.DataSourceFactory;
import com.coder.hms.dao.CompanyDAO;
import com.coder.hms.dao.TransactionManagement;
import com.coder.hms.entities.Company;
import com.coder.hms.utils.LoggingEngine;

public class CompanyDaoImpl implements CompanyDAO, TransactionManagement {

	private Session session;
    private static LoggingEngine logging;
	private DataSourceFactory dataSourceFactory;
	
	public CompanyDaoImpl() {
		dataSourceFactory = new DataSourceFactory();
		DataSourceFactory.createConnection();
        logging = LoggingEngine.getInstance();
	}
	
	@Override
	public Company getCompanyByName(String companyName) {
		Company company = null;
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Company> query = session.createQuery("from Company where title = :companyName", Company.class);
			query.setParameter("companyName", companyName);
			query.setMaxResults(1);
			company = query.getSingleResult();
			
			logging.setMessage("CompanyDaoImpl -> fetching by name :" + companyName);
			
		} catch (NoResultException ex) {
			logging.setMessage("CompanyDaoImpl Error -> "+ex.getLocalizedMessage());
		} finally {
			if(session.isOpen()){session.close();}
		}
		return company;
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companiesList = null;
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Company> query = session.createQuery("from Company", Company.class);
			companiesList = query.getResultList();
			
			logging.setMessage("CompanyDaoImpl -> fetching all companies...");
			
		} catch (HibernateException ex) {
			logging.setMessage("CompanyDaoImpl Error -> "+ex.getLocalizedMessage());
		} finally {
			if(session.isOpen()){session.close();}
		}
		return companiesList;
	}

	@Override
	public boolean saveCompany(Company theCompany) {
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			session.saveOrUpdate(theCompany);
			session.getTransaction().commit();
			logging.setMessage("CompanyDaoImpl -> saving company...");
			return true;
			
		} catch (HibernateException ex) {
			session.getTransaction().rollback();
			logging.setMessage("CompanyDaoImpl Error -> "+ex.getLocalizedMessage());
			return false;
		} finally {
			if(session.isOpen()){session.close();}
		}
	}

	@Override
	public void deleteByName(String selectedCompanyName) {
		try {
			session = dataSourceFactory.getSessionFactory().openSession();
			beginTransactionIfAllowed(session);
			Query<Company> query = session.createQuery("from Company where title = :companyName", Company.class);
			query.setParameter("companyName", selectedCompanyName);
			query.setMaxResults(1);
			session.delete(query.getSingleResult());
			session.getTransaction().commit();
			
			logging.setMessage("CompanyDaoImpl -> deleting company...");
			
		} catch (HibernateException ex) {
			session.getTransaction().rollback();
			logging.setMessage("CompanyDaoImpl Error -> "+ex.getLocalizedMessage());
		} finally {
			if(session.isOpen()){session.close();}
		}
		
	}

	@Override
	public void beginTransactionIfAllowed(Session theSession) {
		if(!theSession.getTransaction().isActive()) {
			theSession.beginTransaction();	
		}else {
			theSession.getTransaction().rollback();
			theSession.beginTransaction();
		}
		
	}
}
