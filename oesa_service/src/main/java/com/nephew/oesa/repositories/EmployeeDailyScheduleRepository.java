package com.nephew.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephew.oesa.entities.employee.EmployeeDailySchedule;

public interface EmployeeDailyScheduleRepository extends JpaRepository<EmployeeDailySchedule, Long> {

}
