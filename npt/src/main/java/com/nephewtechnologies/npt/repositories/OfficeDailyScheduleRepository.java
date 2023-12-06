package com.nephewtechnologies.npt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nephewtechnologies.npt.entities.OfficeDailySchedule;

@Repository
public interface OfficeDailyScheduleRepository extends JpaRepository<OfficeDailySchedule, Long> {

}
