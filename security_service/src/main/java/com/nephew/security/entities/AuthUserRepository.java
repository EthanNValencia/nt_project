package com.nephew.security.entities;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AuthUserRepository extends JpaRepository<AuthUser, Long>{
	
	@Query
	public Optional<AuthUser> findByEmail(String email);
	
}
