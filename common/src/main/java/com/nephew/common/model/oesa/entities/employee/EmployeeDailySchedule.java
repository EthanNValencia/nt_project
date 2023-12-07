package com.nephew.common.model.oesa.entities.employee;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EmployeeDailySchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 3)
	private String day;
	@Column()
	private LocalTime beginTime;
	@Column()
	private LocalTime endTime;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public EmployeeDailySchedule() {
		super();
	}

	public EmployeeDailySchedule(String day, LocalTime beginTime, LocalTime endTime, Employee employee) {
		super();
		this.day = day;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.employee = employee;
	}

	public EmployeeDailySchedule(String day, LocalTime beginTime, LocalTime endTime) {
		super();
		this.day = day;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
}
