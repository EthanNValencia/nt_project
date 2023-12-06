package com.nephewtechnologies.npt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nephewtechnologies.npt.entities.employee.EmployeeDailySchedule;

public interface EmployeeDailyScheduleRepository extends JpaRepository<EmployeeDailySchedule, Long> {

}
