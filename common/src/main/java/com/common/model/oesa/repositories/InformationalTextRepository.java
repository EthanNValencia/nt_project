package com.common.model.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.model.oesa.entities.employee.InformationalText;

public interface InformationalTextRepository extends JpaRepository<InformationalText, Long> {

}
