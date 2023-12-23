package com.nephew.website.repositories;

import com.nephew.website.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	public Optional<Company> findByCompanyUrl(String companyUrl);

}
