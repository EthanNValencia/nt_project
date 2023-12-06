package com.nephewtechnologies.npt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephewtechnologies.npt.entities.website.Website;

public interface WebsiteRepository extends JpaRepository<Website, Long> {

}
