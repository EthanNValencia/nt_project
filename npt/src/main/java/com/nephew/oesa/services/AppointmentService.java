package com.nephewtechnologies.npt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nephewtechnologies.npt.entities.Appointment;
import com.nephewtechnologies.npt.repositories.AppointmentRepository;

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository repo;

	public Appointment saveNewAppointment(Appointment newAppointment) {
		Appointment savedAppointment = repo.save(newAppointment);
		return savedAppointment;
	}
	
}
