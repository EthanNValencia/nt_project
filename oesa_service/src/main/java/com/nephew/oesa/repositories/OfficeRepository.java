package com.nephew.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephew.oesa.entities.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {
	
	
}
