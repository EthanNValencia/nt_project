package com.nephew.oesa.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nephew.oesa.entities.Company;
import com.nephew.oesa.entities.Office;
import com.nephew.oesa.entities.OfficeDailySchedule;
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.repositories.OfficeRepository;

@Service
public class OfficeService {

    Logger logger = LoggerFactory.getLogger(OfficeService.class);
	
	@Autowired
	private OfficeRepository officeRepository;

	@Autowired
	private CompanyService companyService;

	public List<Office> getOffices(String companyUrl) {
		Optional<List<Office>> offices = Optional.of(officeRepository.findByCompanyUrl(companyUrl));
		return offices.get();
	}

	public Office saveOffice(String companyUrl, Office office) {
		office.assignIdToChildren();
		if (office.ifCompanyIsNull()) {
			Company company = companyService.findCompanyByCompanyUrl(companyUrl);
			office.setCompany(company);
		}
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

	public void deleteOffice(long id) {
		Optional<Office> officeOptional = officeRepository.findOfficeByOfficeId(id);
		if (officeOptional.isPresent()) {
			officeRepository.deleteOfficeByOfficeId(id);
			logger.info("Office with ID {} deleted successfully.", id);
		} else {
			logger.warn("Attempted to delete non-existing office with ID {}.", id);
		}
	}

	public List<Office> createNewOffice(String companyUrl) {
		Office office = new Office();
		Company company = companyService.findCompanyByCompanyUrl(companyUrl);
		office.setCompany(company);
		officeRepository.save(office);
		return Optional.of(officeRepository.findByCompanyUrl(companyUrl)).get();
	}

}
