package com.common.model.oesa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.model.oesa.entities.Appointment;
import com.common.model.oesa.repositories.AppointmentRepository;

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository repo;

	public Appointment saveNewAppointment(Appointment newAppointment) {
		Appointment savedAppointment = repo.save(newAppointment);
		return savedAppointment;
	}
	
}
