package com.nephew.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephew.oesa.entities.employee.InformationalText;

public interface InformationalTextRepository extends JpaRepository<InformationalText, Long> {

}
