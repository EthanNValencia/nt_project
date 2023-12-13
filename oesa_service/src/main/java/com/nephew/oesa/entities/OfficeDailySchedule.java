package com.nephew.oesa.entities;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OfficeDailySchedule extends DailySchedule {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "office_id", referencedColumnName = "office_id", nullable = false)
	@JsonIgnoreProperties({ "statesArr", "company", "officeSocialMedialProfile", "schedule", "employees" })
	private Office office;

	public OfficeDailySchedule() {
		super();
	}

	public OfficeDailySchedule(Day day, LocalTime beginTime, LocalTime endTime, Office office) {
		super(day, beginTime, endTime);
		this.office = office;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Override
	public String toString() {
		return "OfficeDailySchedule [id=" + this.getId() + "]";
	}

}
