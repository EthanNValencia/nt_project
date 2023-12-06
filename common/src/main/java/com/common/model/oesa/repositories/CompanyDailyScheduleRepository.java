package com.common.model.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.common.model.oesa.entities.OfficeDailySchedule;

@Repository
public interface CompanyDailyScheduleRepository extends JpaRepository<OfficeDailySchedule, Long> {

}
