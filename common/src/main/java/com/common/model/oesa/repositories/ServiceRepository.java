package com.common.model.oesa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.common.model.oesa.entities.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {
	
	@Query
	public Optional<Services> findByName(String name);
	
	@Query(value = "SELECT * FROM services WHERE name = :name", nativeQuery = true)
	public Optional<Services> findServicesByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM services", nativeQuery = true)
	public Optional<List<Services>> findAllServices();
	
	@Query(value = "SELECT * FROM services WHERE name != :name", nativeQuery = true)
	public Optional<List<Services>> findAllExcept(@Param("name") String name);
	
}