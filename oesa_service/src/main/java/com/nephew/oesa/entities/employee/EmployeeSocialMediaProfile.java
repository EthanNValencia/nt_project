package com.nephew.oesa.entities.employee;

import com.nephew.oesa.entities.SocialMediaProfile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EmployeeSocialMediaProfile extends SocialMediaProfile {
	
	@OneToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employee employee;
	
	public EmployeeSocialMediaProfile() {
		super();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeeSocialMediaProfile [employee=" + employee.getId() + "]";
	}
	
}
