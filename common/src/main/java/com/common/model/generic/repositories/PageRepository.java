package com.common.model.generic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.model.generic.entities.Page;

public interface PageRepository extends JpaRepository<Page, Long> {

}
