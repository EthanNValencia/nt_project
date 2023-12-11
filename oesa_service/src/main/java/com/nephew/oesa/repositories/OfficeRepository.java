package com.nephew.oesa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nephew.oesa.entities.FAQs;
import com.nephew.oesa.entities.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {

	@Query("SELECT o FROM Office o WHERE o.company.id = :companyId")
	public List<Office> findByCompanyId(@Param("companyId") long companyId);
	
	@Query("SELECT o FROM Office o WHERE o.company.companyUrl = :companyUrl")
	public List<Office> findByCompanyUrl(@Param("companyUrl") String companyUrl);
	
}
