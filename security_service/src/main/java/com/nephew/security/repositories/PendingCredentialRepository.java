package com.nephew.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephew.security.entities.PendingCredential;

public interface PendingCredentialRepository extends JpaRepository<PendingCredential, Long>{
	
    Optional<PendingCredential> findByPendingCode(String pendingCode);
    
}
