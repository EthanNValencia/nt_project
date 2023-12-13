package com.nephew.oesa.entities;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

@MappedSuperclass
public class DailySchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, length = 10)
	private Day day = Day.Undefined;
	@Column(nullable = false)
	private LocalTime beginTime;
	@Column(nullable = false)
	private LocalTime endTime;
	@Transient
	private Day[] daysArr;
	
	public DailySchedule(Day day, LocalTime beginTime, LocalTime endTime) {
		super();
		this.day = day;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.daysArr = Day.values();
	}
	
	public DailySchedule() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
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
	public Day[] getDaysArr() {
		return daysArr;
	}
	public void setDaysArr(Day[] daysArr) {
		this.daysArr = daysArr;
	}
	
	
}
