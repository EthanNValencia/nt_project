package com.nephew.es.errorservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nephew.es.errorservice.entities.FrontEndError;

@Repository
public interface FrontEndErrorRepository extends JpaRepository<FrontEndError, Long> {

}
