package com.common.model.generic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.model.generic.entities.Website;

public interface WebsiteRepository extends JpaRepository<Website, Long> {

}
