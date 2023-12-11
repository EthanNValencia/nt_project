package com.nephew.oesa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nephew.oesa.entities.Office;
import com.nephew.oesa.entities.OfficeDailySchedule;
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.repositories.OfficeRepository;

@Service
public class OfficeService {
	@Autowired
	private OfficeRepository officeRepository;
	
	public List<Office> getOffices() {
		Optional<List<Office>> offices = Optional.of(officeRepository.findAll());
		return offices.get();
	}

	public Office saveOffice(Office office) {
	    office = officeRepository.save(office);
	    office.assignIdToChildren();
	    return office;
	}

	public void assignOfficeIdToChildren(Office office) {
	    for (OfficeDailySchedule schedule : office.getSchedule()) {
	        schedule.setOffice(office);
	    }
	    for (Employee employee : office.getEmployees()) {
	        employee.setOffice(office);
	    }
	    System.out.println(office.getOfficeSocialMedialProfile());
	    if (office.getOfficeSocialMedialProfile() != null) {
	        office.getOfficeSocialMedialProfile().setOffice(office);
	    }
	    System.out.println(office.getOfficeSocialMedialProfile());
	}
	
}
