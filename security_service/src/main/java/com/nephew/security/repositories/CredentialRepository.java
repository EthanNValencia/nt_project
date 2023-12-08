package com.nephew.security.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nephew.security.entities.Credential;


public interface CredentialRepository extends JpaRepository<Credential, Long>{
	
	@Query
	public Optional<Credential> findByEmail(String email);
	
}
