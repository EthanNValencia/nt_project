package com.nephew.common.dto;

import java.time.LocalDateTime;

import com.nephew.common.model.oesa.entities.Appointment;

public class AppointmentDto {
	
	private LocalDateTime beginTime;
	private LocalDateTime endTime;

	public AppointmentDto(Appointment appointment) {
		super();
		this.beginTime = appointment.getBeginTime();
		this.endTime = appointment.getEndTime();
	}

	public AppointmentDto() {
		super();
	}

	public AppointmentDto(LocalDateTime appointmentBeginTime, LocalDateTime appointmentEndTime) {
		super();
		this.beginTime = appointmentBeginTime;
		this.endTime = appointmentEndTime;
	}

	public AppointmentDto(CreateAppointmentDto appointment) {
		super();
		this.beginTime = appointment.getAppointmentBeginTime();
		this.endTime = appointment.getAppointmentEndTime();
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

	@Override
	public String toString() {
		return "AppointmentDto [beginTime=" + beginTime + ", endTime=" + endTime + "]";
	}

}
