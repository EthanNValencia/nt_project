package com.nephewtechnologies.npt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephewtechnologies.npt.entities.employee.InformationalText;

public interface InformationalTextRepository extends JpaRepository<InformationalText, Long> {

}
