package com.nephew.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephew.security.entities.PendingCredential;

public interface PendingCredentialRepository extends JpaRepository<PendingCredential, Long>{

}
