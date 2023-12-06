package com.common.dto;

import java.time.LocalDateTime;

public class CreateAppointmentDto {
	
	private String employeeFirstName;
	private String employeeMiddleName;
	private String employeeLastName;
	private String appointmentFirstName;
	private String appointmentLastName;
	private String appointmentEmail;
	private String appointmentPhoneNumber;
	private LocalDateTime appointmentBeginTime;
	private LocalDateTime appointmentEndTime;
	private String appointmentNotes;
	private String serviceName;
	
	public CreateAppointmentDto() {
		super();
	}
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeMiddleName() {
		return employeeMiddleName;
	}
	public void setEmployeeMiddleName(String employeeMiddleName) {
		this.employeeMiddleName = employeeMiddleName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getAppointmentFirstName() {
		return appointmentFirstName;
	}
	public void setAppointmentFirstName(String appointmentFirstName) {
		this.appointmentFirstName = appointmentFirstName;
	}
	public String getAppointmentLastName() {
		return appointmentLastName;
	}
	public void setAppointmentLastName(String appointmentLastName) {
		this.appointmentLastName = appointmentLastName;
	}
	public String getAppointmentEmail() {
		return appointmentEmail;
	}
	public void setAppointmentEmail(String appointmentEmail) {
		this.appointmentEmail = appointmentEmail;
	}
	public String getAppointmentPhoneNumber() {
		return appointmentPhoneNumber;
	}
	public void setAppointmentPhoneNumber(String appointmentPhoneNumber) {
		this.appointmentPhoneNumber = appointmentPhoneNumber;
	}
	public LocalDateTime getAppointmentBeginTime() {
		return appointmentBeginTime;
	}
	public void setAppointmentBeginTime(LocalDateTime appointmentBeginTime) {
		this.appointmentBeginTime = appointmentBeginTime;
	}
	public LocalDateTime getAppointmentEndTime() {
		return appointmentEndTime;
	}
	public void setAppointmentEndTime(LocalDateTime appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}
	public String getAppointmentNotes() {
		return appointmentNotes;
	}
	public void setAppointmentNotes(String appointmentNotes) {
		this.appointmentNotes = appointmentNotes;
	}

	@Override
	public String toString() {
		return "CreateAppointmentDto [employeeFirstName=" + employeeFirstName + ", employeeMiddleName="
				+ employeeMiddleName + ", employeeLastName=" + employeeLastName + ", appointmentFirstName="
				+ appointmentFirstName + ", appointmentLastName=" + appointmentLastName + ", appointmentEmail="
				+ appointmentEmail + ", appointmentPhoneNumber=" + appointmentPhoneNumber + ", appointmentBeginTime="
				+ appointmentBeginTime + ", appointmentEndTime=" + appointmentEndTime + ", appointmentNotes="
				+ appointmentNotes + ", serviceName=" + serviceName + "]";
	}
	
	
}
