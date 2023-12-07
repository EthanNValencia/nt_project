package com.nephew.oesa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nephew.oesa.entities.OfficeDailySchedule;

@Repository
public interface OfficeDailyScheduleRepository extends JpaRepository<OfficeDailySchedule, Long> {

}
