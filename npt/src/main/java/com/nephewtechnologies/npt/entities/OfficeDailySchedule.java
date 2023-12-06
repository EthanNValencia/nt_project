package com.nephewtechnologies.npt.entities;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OfficeDailySchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, length = 3)
	private String day;
	@Column(nullable = false)
	private LocalTime beginTime;
	@Column(nullable = false)
	private LocalTime endTime;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "office_id", referencedColumnName = "office_id", nullable = false)
	// @JsonIgnoreProperties({"schedule", "employees"})
	@JsonIgnore
	private Office office;

	public OfficeDailySchedule() {
		super();
	}

	public OfficeDailySchedule(String day, LocalTime beginTime, LocalTime endTime, Office office) {
		super();
		this.day = day;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.office = office;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public LocalTime getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(LocalTime beginTime) {
		this.beginTime = beginTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "OfficeDailySchedule [id=" + id + "]";
	}

	

}
