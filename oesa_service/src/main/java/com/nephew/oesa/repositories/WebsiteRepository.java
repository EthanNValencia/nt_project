package com.nephew.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nephew.oesa.entities.website.Website;

public interface WebsiteRepository extends JpaRepository<Website, Long> {
	
	@Query("SELECT w FROM Website w WHERE w.company.id = :companyId")
	public Website findByCompanyId(@Param("companyId") long companyId);
	
	@Query("SELECT w FROM Website w WHERE w.company.companyUrl = :companyUrl")
	public Website findByCompanyUrl(@Param("companyUrl") String companyUrl);
	
}
