package com.nephew.oesa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nephew.oesa.entities.Company;
import com.nephew.oesa.repositories.CompanyRepository;

@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	
	public Company findCompanyByCompanyUrl(String companyUrl) {
		Company company = companyRepository.findByCompanyUrl(companyUrl).get();
		return company;
	}
}
