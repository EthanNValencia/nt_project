package com.nephew.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephew.oesa.entities.website.Page;

public interface PageRepository extends JpaRepository<Page, Long> {

}
