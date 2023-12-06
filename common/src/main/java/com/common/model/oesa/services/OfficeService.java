package com.common.model.oesa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.model.oesa.entities.Office;
import com.common.model.oesa.entities.OfficeDailySchedule;
import com.common.model.oesa.entities.employee.Employee;
import com.common.model.oesa.repositories.OfficeRepository;

@Service
public class OfficeService {
	@Autowired
	private OfficeRepository officeRepository;
	
	public List<Office> getOffices() {
		Optional<List<Office>> offices = Optional.of(officeRepository.findAll());
		return offices.get();
	}

	public Office saveOffice(Office office) {
	    assignOfficeIdToChildren(office);
	    office = officeRepository.save(office);
	    return office;
	}

	public void assignOfficeIdToChildren(Office office) {
	    for (OfficeDailySchedule schedule : office.getSchedule()) {
	        schedule.setOffice(office);
	    }
	    for (Employee employee : office.getEmployees()) {
	        employee.setOffice(office);
	    }
	    if (office.getOfficeSocialMedialProfile() != null) {
	        office.getOfficeSocialMedialProfile().setOffice(office);
	    }
	}
	
}
