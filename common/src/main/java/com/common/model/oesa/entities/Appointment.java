package com.common.model.oesa.entities;

import java.time.LocalDateTime;

import com.common.dto.CreateAppointmentDto;
import com.common.model.oesa.entities.employee.Employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 128)
	private String firstName;
	@Column(length = 128)
	private String lastName;
	@Column(length = 128)
	private String email;
	@Column(length = 20)
	private String phoneNumber;
	private LocalDateTime beginTime;
	private LocalDateTime endTime;
	@Column(columnDefinition = "TEXT")
	private String notes;
	
	private boolean appointmentApproved = false;
	private boolean emailSent = false;
	private boolean smsSent = false;
	private boolean appointmentModified = false;
	private boolean appointmentModifiedApproved = false;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	public Appointment(CreateAppointmentDto app, Employee employee) {
		this.beginTime = app.getAppointmentBeginTime();
		this.endTime = app.getAppointmentEndTime();
		this.firstName = app.getAppointmentFirstName();
		this.lastName = app.getAppointmentLastName();
		this.phoneNumber = app.getAppointmentPhoneNumber();
		this.email = app.getAppointmentEmail();
		this.notes = app.getAppointmentNotes();
		this.employee = new Employee(employee.getId());
	}
	
	public boolean isAppointmentApproved() {
		return appointmentApproved;
	}

	public void setAppointmentApproved(boolean appointmentApproved) {
		this.appointmentApproved = appointmentApproved;
	}

	public boolean isEmailSent() {
		return emailSent;
	}

	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}

	public boolean isSmsSent() {
		return smsSent;
	}

	public void setSmsSent(boolean smsSent) {
		this.smsSent = smsSent;
	}

	public boolean isAppointmentModified() {
		return appointmentModified;
	}

	public void setAppointmentModified(boolean appointmentModified) {
		this.appointmentModified = appointmentModified;
	}

	public boolean isAppointmentModifiedApproved() {
		return appointmentModifiedApproved;
	}

	public void setAppointmentModifiedApproved(boolean appointmentModifiedApproved) {
		this.appointmentModifiedApproved = appointmentModifiedApproved;
	}

	public LocalDateTime getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(LocalDateTime beginTime) {
		this.beginTime = beginTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Appointment() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", beginTime=" + beginTime + ", endTime=" + endTime + ", notes="
				+ notes + ", employee=" + employee.getId() + "]";
	}

	

}
