package com.common.model.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.model.oesa.entities.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {
	
	
}
