package com.nephew.oesa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nephew.oesa.dto.EmailDto;
import com.nephew.oesa.dto.OfficeDto;
import com.nephew.oesa.entities.FAQs;
import com.nephew.oesa.entities.Office;
import com.nephew.oesa.entities.Services;
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.entities.website.Page;
import com.nephew.oesa.entities.website.Website;
import com.nephew.oesa.repositories.OfficeRepository;
import com.nephew.oesa.services.EmployeeService;
import com.nephew.oesa.services.FAQsService;
import com.nephew.oesa.services.OfficeService;
import com.nephew.oesa.services.ServicesService;
import com.nephew.oesa.services.WebsiteService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class PrivateController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private OfficeService officeService;

	@Autowired
	private FAQsService faqsService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private WebsiteService websiteService;
	
	@Autowired
	private ServicesService servicesService;

	@GetMapping("/offices/")
	public ResponseEntity<List<Office>> getOffices() {
		List<Office> offices = officeService.getOffices();
		return new ResponseEntity<List<Office>>(offices, HttpStatus.OK);
	}
	
	@PostMapping("/offices/")
	public ResponseEntity<Office> saveOffice(@RequestBody Office office) {
		Office savedOffice = officeService.saveOffice(office);
		return new ResponseEntity<Office>(savedOffice, HttpStatus.OK);
	}

	@PutMapping("/faqs/answer-question")
	public ResponseEntity<FAQs> answerFAQ(@RequestBody FAQs faqs) {
		FAQs answeredFaq = faqsService.saveAnsweredQuestion(faqs);
		return new ResponseEntity<FAQs>(answeredFaq, HttpStatus.OK);
	}

	@GetMapping("/faqs/get-unanswered-questions")
	public ResponseEntity<ArrayList<FAQs>> getAllUnansweredQuestions() {
		return new ResponseEntity<ArrayList<FAQs>>(faqsService.getAllUnansweredQuestions(), HttpStatus.OK);
	}

	@GetMapping("/faqs/get-all")
	public ResponseEntity<List<FAQs>> getAllFaqs() {
		return new ResponseEntity<List<FAQs>>(faqsService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/employees/")
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.getAllOrderById(), HttpStatus.OK);
	}
	
	@PostMapping("/employees/informational/{position}")
	public ResponseEntity<Employee>  addInformationalText(@PathVariable(value = "position") int position, @RequestBody Employee employee) {
		return new ResponseEntity<Employee> (employeeService.addInformationalText(position, employee), HttpStatus.OK);
	}
	
	@PostMapping("/employees/biographical/{position}")
	public ResponseEntity<Employee> addBiographicalText(@PathVariable(value = "position") int position, @RequestBody Employee employee) {
		return new ResponseEntity<Employee> (employeeService.addBiographicalText(position, employee), HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<Employee>(employeeService.getById(id), HttpStatus.OK);
	}
	
	@PutMapping("/employees/")
	public ResponseEntity<Employee> putEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
	}

	@DeleteMapping("/faqs/{id}") // @PathVariable(value = "id") String type
	public ResponseEntity<Void> deleteFaqById(@PathVariable(value = "id") Long id) {
		faqsService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/faqs/")
	public ResponseEntity<FAQs> updateFaq(@RequestBody FAQs faqs) {
		return new ResponseEntity<FAQs>(faqsService.saveFaq(faqs), HttpStatus.OK);
	}
	
	@GetMapping("/services/")
	public ResponseEntity<List<Services>> getServices() {
		return new ResponseEntity<List<Services>>(servicesService.getServices(), HttpStatus.OK);
	}
	
	@PutMapping("/services/")
	public ResponseEntity<List<Services>> updateServices(@RequestBody List<Services> services) {
		return new ResponseEntity<List<Services>>(servicesService.updateServices(services), HttpStatus.OK);
	}
	
	@DeleteMapping("/services/{id}")
	public ResponseEntity<Void> deleteService(@PathVariable(value = "id") Long id) {
		servicesService.deleteService(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/website/")
	public ResponseEntity<Website> getWebsite() {
		return new ResponseEntity<Website>(websiteService.getWebsite(), HttpStatus.OK);
	}

	@PostMapping("/website/")
	public ResponseEntity<Website> updateWebsite(@RequestBody Website website) {
		return new ResponseEntity<Website>(websiteService.updateWebsite(website), HttpStatus.OK);
	}
	
	@PostMapping("/website/pages") 
	public ResponseEntity<Website> createPage(@RequestBody Website website) {
		return new ResponseEntity<Website>(websiteService.createPage(website), HttpStatus.OK);
	}
	
	@PostMapping("/website/pages/paragraph") 
	public ResponseEntity<Website> createParagraph(@RequestBody Page page) {
		return new ResponseEntity<Website>(websiteService.createParagraph(page), HttpStatus.OK);
	}

	@PostMapping("/notify/email/")
	public ResponseEntity<Void> notifyByEmail(@RequestBody EmailDto emailDto) {
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("http://localhost:8200/api/v1/public/send/", emailDto, Void.class);
		return responseEntity;
	}

}
