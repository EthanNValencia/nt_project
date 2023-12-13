package com.nephew.oesa.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nephew.oesa.entities.services.ServiceText;
import com.nephew.oesa.entities.services.Services;
import com.nephew.oesa.repositories.ServiceRepository;

@Service
public class ServicesService {
	
	@Autowired
	private ServiceRepository repo;
	
	@Deprecated
	public List<Services> getAllSpecialities() {
		List<Services> specialities = repo.findAllServices().get();
		return specialities;
	}

	public List<Services> getServices() {
		List<Services> services = repo.findAllServices().get();
		return services;
	}

	public List<Services> updateServices(List<Services> services) {
		return repo.saveAll(services);
	}

	public void deleteService(Long id) {
		repo.deleteById(id);
	}

	public Services saveService(Services service) {
		return repo.save(service);
	}

	public Services addNewText(int position, Services service) {
		ServiceText text = new ServiceText();
		text.setPosition(position);
		service.getServiceTexts().add(text);
		repo.save(service);
		return service;
	}

	public List<Services> findAllById(Set<Long> serviceIds) {
		return repo.findAllById(serviceIds);
	}
}
