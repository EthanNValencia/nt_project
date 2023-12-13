package com.nephew.oesa.entities.employee;

import java.time.LocalTime;

import com.nephew.oesa.entities.DailySchedule;
import com.nephew.oesa.entities.Day;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EmployeeDailySchedule extends DailySchedule {
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public EmployeeDailySchedule() {
		super();
	}

	public EmployeeDailySchedule(Day day, LocalTime beginTime, LocalTime endTime, Employee employee) {
		super(day, beginTime, endTime);
		this.employee = employee;
	}
	
	public EmployeeDailySchedule(Day day, LocalTime beginTime, LocalTime endTime) {
		super(day, beginTime, endTime);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}
