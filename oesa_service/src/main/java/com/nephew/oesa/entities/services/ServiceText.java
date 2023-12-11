package com.nephew.oesa.entities.services;

import com.nephew.oesa.entities.text.Text;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ServiceText extends Text {
	
	@ManyToOne
	@JoinColumn(name = "service_id", referencedColumnName = "id")
	private Services service;

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}
	
}
