package com.nephew.faqs.repositories;

import com.nephew.faqs.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	public Optional<Company> findByCompanyUrl(String companyUrl);

}
