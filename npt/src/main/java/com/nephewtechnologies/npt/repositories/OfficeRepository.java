package com.nephewtechnologies.npt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nephewtechnologies.npt.entities.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {
	
	
}
