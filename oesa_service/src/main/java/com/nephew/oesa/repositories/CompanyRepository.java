package com.nephew.oesa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nephew.oesa.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	public Optional<Company> findByCompanyUrl(String companyUrl);

}
