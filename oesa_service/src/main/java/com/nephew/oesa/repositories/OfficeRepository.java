package com.nephew.oesa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nephew.oesa.entities.Office;

import jakarta.transaction.Transactional;

public interface OfficeRepository extends JpaRepository<Office, Long> {

	@Query("SELECT o FROM Office o WHERE o.company.id = :companyId")
	public List<Office> findByCompanyId(@Param("companyId") long companyId);

	@Query("SELECT o FROM Office o WHERE o.company.companyUrl = :companyUrl")
	public List<Office> findByCompanyUrl(@Param("companyUrl") String companyUrl);

	@Modifying
	@Transactional
	@Query("DELETE FROM Office o WHERE o.officeId = :id")
	public void deleteOfficeByOfficeId(@Param("id") long id);

	@Query("SELECT o FROM Office o WHERE o.officeId = :id")
	public Optional<Office> findOfficeByOfficeId(@Param("id") long id);
}
