package com.nephew.oesa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nephew.oesa.dto.AppointmentDto;
import com.nephew.oesa.dto.CreateAppointmentDto;
import com.nephew.oesa.dto.EmployeeDto;
import com.nephew.oesa.dto.FAQsDto;
import com.nephew.oesa.dto.OfficeDto;
import com.nephew.oesa.entities.Appointment;
import com.nephew.oesa.entities.FAQs;
import com.nephew.oesa.entities.Office;
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.entities.services.Services;
import com.nephew.oesa.services.AppointmentService;
import com.nephew.oesa.services.DtoService;
import com.nephew.oesa.services.EmployeeService;
import com.nephew.oesa.services.FAQsService;
import com.nephew.oesa.services.OfficeService;
import com.nephew.oesa.services.ServicesService;
import com.nephew.oesa.services.ValidationSerivce;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

	@Autowired
	private DtoService dtoService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private FAQsService faqsService;

	@Autowired
	private ServicesService specialtyService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private ValidationSerivce validator;

	@Autowired
	private OfficeService officeService;

	// This is good for testing for high latency situations on the front end. 
	private void sleepApi() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok("ok");
	}

	@GetMapping("{companyUrl}/employees/")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(@PathVariable(value = "companyUrl") String companyUrl) {
		List<Employee> employees = employeeService.getAll();
		List<EmployeeDto> employeesDto = dtoService.convertEmployeesDto(employees, true);
		return new ResponseEntity<List<EmployeeDto>>(employeesDto, HttpStatus.OK);
	}

	@PostMapping("{companyUrl}/employees/services/")
	public ResponseEntity<List<EmployeeDto>> findEmployeeBySpeciality(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody List<Services> problemAreas) {
		List<EmployeeDto> employees;
		if (problemAreas.size() == 1) {
			employees = dtoService.convertEmployeesDto(employeeService.findEmployeesByProblemArea(problemAreas), false);
		} else {
			employees = dtoService.convertEmployeesDto(employeeService.findEmployeesByProblemAreas(problemAreas),
					false);
		}
		return new ResponseEntity<List<EmployeeDto>>(employees, HttpStatus.OK);
	}

	@PostMapping("{companyUrl}/faqs/")
	public ResponseEntity<FAQsDto> saveNewFAQ(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody FAQs faqs) {
		FAQs savedFaq = faqsService.saveNewQuestion(faqs);
		FAQsDto faqsDto = dtoService.convertFAQsDto(savedFaq);
		return new ResponseEntity<FAQsDto>(faqsDto, HttpStatus.OK);
	}

	@GetMapping("{companyUrl}/faqs/")
	public ResponseEntity<ArrayList<FAQsDto>> getAllAnsweredQuestions(@PathVariable(value = "companyUrl") String companyUrl) {
		ArrayList<FAQs> faqs = faqsService.getAllAnsweredQuestions();
		ArrayList<FAQsDto> faqsDto = dtoService.convertFAQsDto(faqs);
		return new ResponseEntity<ArrayList<FAQsDto>>(faqsDto, HttpStatus.OK);
	}

	@GetMapping("{companyUrl}/services")
	public ResponseEntity<List<Services>> getProblemAreaCategories(@PathVariable(value = "companyUrl") String companyUrl) {
		return new ResponseEntity<List<Services>>(specialtyService.getAllSpecialities(), HttpStatus.OK);
	}

	@PostMapping("{companyUrl}/appointment/")
	public ResponseEntity<AppointmentDto> saveNewAppointment(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody CreateAppointmentDto appointment) {
		Employee employee = employeeService.findEmployeeByName(appointment.getEmployeeFirstName(),
				appointment.getEmployeeMiddleName(), appointment.getEmployeeLastName());
		EmployeeDto employeeDto = dtoService.convertEmployeeDto(employee, false);
		if (!validator.isNewAppointmentValid(employeeDto, appointment.getAppointmentBeginTime(),
				appointment.getAppointmentEndTime())) {
			return new ResponseEntity<AppointmentDto>(new AppointmentDto(appointment), HttpStatus.CONFLICT);
		}
		Appointment newAppointment = new Appointment(appointment, employee);
		newAppointment = appointmentService.saveNewAppointment(newAppointment);
		AppointmentDto newAppointmentDto = dtoService.convertToAppointmentDto(newAppointment);
		// System.out.println("New Appointment: " + newAppointmentDto);
		return new ResponseEntity<AppointmentDto>(newAppointmentDto, HttpStatus.OK);
	}

	@GetMapping("{companyUrl}/office/")
	public ResponseEntity<List<OfficeDto>> getOffices(@PathVariable(value = "companyUrl") String companyUrl) {
		List<Office> offices = officeService.getOffices(companyUrl);
		List<OfficeDto> officesDto = dtoService.convertOfficesToDtos(offices);
		return new ResponseEntity<List<OfficeDto>>(officesDto, HttpStatus.OK);
	}

}
