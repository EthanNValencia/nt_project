package com.nephewtechnologies.npt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephewtechnologies.npt.entities.website.Page;

public interface PageRepository extends JpaRepository<Page, Long> {

}
