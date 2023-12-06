package com.common.model.oesa.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.model.oesa.entities.Appointment;
import com.common.model.oesa.entities.Services;
import com.common.model.oesa.entities.employee.BiographicalText;
import com.common.model.oesa.entities.employee.Employee;
import com.common.model.oesa.entities.employee.EmployeeDailySchedule;
import com.common.model.oesa.entities.employee.InformationalText;
import com.common.model.oesa.repositories.EmployeeRepository;
import com.common.model.oesa.repositories.ServiceRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private ServiceRepository serviceRepo;

	public List<Employee> getAll() {
		List<Employee> employees = employeeRepo.findAll();
		return employees;
	}

	public List<Employee> getAllOrderById() {
		List<Employee> employees = employeeRepo.findAllByOrderById();
		return employees;
	}

	public List<Employee> findEmployeesByProblemArea(List<Services> specialities) {
		List<Employee> employees = employeeRepo.selectListEmployeeThatHasSpeciality(specialities.get(0).getName())
				.get();
		return employees;
	}

	public HashSet<Employee> findEmployeesByProblemAreas(List<Services> specialities) {
		HashSet<Employee> matches = new HashSet<>();
		List<Employee> allEmployees = employeeRepo.findAll();
		for (Employee employee : allEmployees) {
			for (Services problemArea : specialities) {
				if (employee.getServices().contains(problemArea)) {
					System.out.println("*** " + employee.getAppointments());
					matches.add(employee);
				}
			}
		}
		return matches;
	}

	public Employee findEmployeeByName(String appointmentFirstName, String employeeMiddleName,
			String appointmentLastName) {
		Optional<Employee> employee = employeeRepo.findByFirstNameAndLastName(appointmentFirstName,
				appointmentLastName);
		return employee.get();
	}

	public Employee updateEmployee(Employee employee) {
		assignEmployeeIdToChildren(employee);
		// System.out.println("Before: " + employee);
		try {
			updateEmployeeServices(employee.getId(), employee.returnServicesIdAsSet());
		} catch (Exception e) {
			throw new RuntimeException("Error updating employee services", e);
		}
		// System.out.println("After: " + employee);
		employee = employeeRepo.save(employee);
		// System.out.println("After Save: " + employee);
		return employee;
	}

	public void assignEmployeeIdToChildren(Employee employee) {
		for (BiographicalText text : employee.getBiographicalTexts()) {
			text.setEmployee(employee);
		}
		for (InformationalText text : employee.getInformationalTexts()) {
			text.setEmployee(employee);
		}
		for (EmployeeDailySchedule schedule : employee.getSchedule()) {
			schedule.setEmployee(employee);
		}
		for (Appointment appointment : employee.getAppointments()) {
			appointment.setEmployee(employee);
		}
		if (employee.getProfile() != null) {
			employee.getProfile().setEmployee(employee);
		}
	}

	public Employee updateEmployeeServices(Long employeeId, Set<Long> serviceIds) throws Exception {
		Employee employee = employeeRepo.findById(employeeId).orElse(null);
		if (employee != null) {
			List<Services> services = serviceRepo.findAllById(serviceIds);
			employee.setServices(new HashSet<>(services));
			return employeeRepo.save(employee);
		}
		throw new Exception("Employee not found with ID: " + employeeId);
	}

	public Employee getById(Long id) {
		return employeeRepo.findById(id).get();
	}

	public Employee addInformationalText(int position, Employee employee) {
		employee = employeeRepo.findById(employee.getId()).orElse(null);
		if (employee != null) {
			InformationalText text = new InformationalText();
			text.setEmployee(employee);
			text.setPosition(position);
			employee.getInformationalTexts().add(text);
			return employeeRepo.save(employee);
		}
		return null;
	}

	public Employee addBiographicalText(int position, Employee employee) {
		employee = employeeRepo.findById(employee.getId()).orElse(null);
		if (employee != null) {
			BiographicalText text = new BiographicalText();
			text.setEmployee(employee);
			text.setPosition(position);
			employee.getBiographicalTexts().add(text);
			return employeeRepo.save(employee);
		}
		return null;
	}

	

}
