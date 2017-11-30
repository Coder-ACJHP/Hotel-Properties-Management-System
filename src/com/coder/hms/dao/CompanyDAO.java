package com.coder.hms.dao;

import java.util.List;

import com.coder.hms.entities.Company;

public interface CompanyDAO {

	public Company getCompanyByName(String companyName);
	
	public List<Company> getAllCompanies();
	
	public boolean saveCompany(Company theCompany);
	
	public void deleteByName(String selectedCompanyName);
}
