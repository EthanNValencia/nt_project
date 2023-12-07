package com.nephew.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephew.oesa.entities.website.Website;

public interface WebsiteRepository extends JpaRepository<Website, Long> {

}
