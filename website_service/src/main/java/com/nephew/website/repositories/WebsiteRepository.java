package com.nephew.website.repositories;

import com.nephew.website.entities.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WebsiteRepository extends JpaRepository<Website, Long> {
	
	@Query("SELECT w FROM Website w WHERE w.company.id = :companyId")
	public Website findByCompanyId(@Param("companyId") long companyId);
	
	@Query("SELECT w FROM Website w WHERE w.company.companyUrl = :companyUrl")
	public Website findByCompanyUrl(@Param("companyUrl") String companyUrl);
	
}
