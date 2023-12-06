package com.common.model.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.model.oesa.entities.employee.EmployeeDailySchedule;

public interface EmployeeDailyScheduleRepository extends JpaRepository<EmployeeDailySchedule, Long> {

}
