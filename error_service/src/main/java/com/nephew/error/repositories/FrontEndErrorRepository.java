package com.nephew.error.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nephew.error.entities.FrontEndError;

@Repository
public interface FrontEndErrorRepository extends JpaRepository<FrontEndError, Long> {

}
