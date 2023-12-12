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
import com.nephew.oesa.entities.employee.Employee;
import com.nephew.oesa.entities.services.Services;
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

	@GetMapping("{companyUrl}/offices/")
	public ResponseEntity<List<Office>> getOffices(@PathVariable(value = "companyUrl") String companyUrl) {
		List<Office> offices = officeService.getOffices(companyUrl);
		return new ResponseEntity<List<Office>>(offices, HttpStatus.OK);
	}
	
	@PostMapping("{companyUrl}/offices/")
	public ResponseEntity<Office> saveOffice(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody Office office) {
		Office savedOffice = officeService.saveOffice(companyUrl, office);
		return new ResponseEntity<Office>(savedOffice, HttpStatus.OK);
	}
	
	@GetMapping("{companyUrl}/offices/create")
	public ResponseEntity<List<Office>> createNewOffice(@PathVariable(value = "companyUrl") String companyUrl) {
		List<Office> offices = officeService.createNewOffice(companyUrl);
		return new ResponseEntity<List<Office>>(offices, HttpStatus.OK);
	}
	
	@DeleteMapping("{companyUrl}/offices/{id}")
	public ResponseEntity<Void> deleteOffice(@PathVariable(value = "companyUrl") String companyUrl, @PathVariable(value = "id") long id) {
		officeService.deleteOffice(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("{companyUrl}/faqs/answer-question")
	public ResponseEntity<FAQs> answerFAQ(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody FAQs faqs) {
		FAQs answeredFaq = faqsService.saveAnsweredQuestion(faqs);
		return new ResponseEntity<FAQs>(answeredFaq, HttpStatus.OK);
	}

	@GetMapping("{companyUrl}/faqs/get-unanswered-questions")
	public ResponseEntity<ArrayList<FAQs>> getAllUnansweredQuestions(@PathVariable(value = "companyUrl") String companyUrl) {
		return new ResponseEntity<ArrayList<FAQs>>(faqsService.getAllUnansweredQuestions(), HttpStatus.OK);
	}

	@GetMapping("{companyUrl}/faqs/get-all")
	public ResponseEntity<List<FAQs>> getAllFaqs(@PathVariable(value = "companyUrl") String companyUrl) {
		return new ResponseEntity<List<FAQs>>(faqsService.getAll(), HttpStatus.OK);
	}

	@GetMapping("{companyUrl}/employees/")
	public ResponseEntity<List<Employee>> getEmployees(@PathVariable(value = "companyUrl") String companyUrl) {
		return new ResponseEntity<List<Employee>>(employeeService.getAllOrderById(), HttpStatus.OK);
	}
	
	@PostMapping("{companyUrl}/employees/informational/{position}")
	public ResponseEntity<Employee>  addInformationalText(@PathVariable(value = "companyUrl") String companyUrl, @PathVariable(value = "position") int position, @RequestBody Employee employee) {
		return new ResponseEntity<Employee> (employeeService.addInformationalText(position, employee), HttpStatus.OK);
	}
	
	@PostMapping("{companyUrl}/employees/biographical/{position}")
	public ResponseEntity<Employee> addBiographicalText(@PathVariable(value = "companyUrl") String companyUrl, @PathVariable(value = "position") int position, @RequestBody Employee employee) {
		return new ResponseEntity<Employee> (employeeService.addBiographicalText(position, employee), HttpStatus.OK);
	}
	
	@GetMapping("{companyUrl}/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "companyUrl") String companyUrl, @PathVariable(value = "id") Long id) {
		return new ResponseEntity<Employee>(employeeService.getById(id), HttpStatus.OK);
	}
	
	@PutMapping("{companyUrl}/employees/")
	public ResponseEntity<Employee> putEmployee(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
	}

	@DeleteMapping("{companyUrl}/faqs/{id}") // @PathVariable(value = "id") String type
	public ResponseEntity<Void> deleteFaqById(@PathVariable(value = "companyUrl") String companyUrl, @PathVariable(value = "id") Long id) {
		faqsService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("{companyUrl}/faqs/")
	public ResponseEntity<FAQs> updateFaq(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody FAQs faqs) {
		return new ResponseEntity<FAQs>(faqsService.saveFaq(faqs), HttpStatus.OK);
	}
	
	@GetMapping("{companyUrl}/services/")
	public ResponseEntity<List<Services>> getServices(@PathVariable(value = "companyUrl") String companyUrl) {
		return new ResponseEntity<List<Services>>(servicesService.getServices(), HttpStatus.OK);
	}
	
	@PutMapping("{companyUrl}/services/")
	public ResponseEntity<List<Services>> updateServices(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody List<Services> services) {
		return new ResponseEntity<List<Services>>(servicesService.updateServices(services), HttpStatus.OK);
	}
	
	@DeleteMapping("{companyUrl}/services/{id}")
	public ResponseEntity<Void> deleteService(@PathVariable(value = "companyUrl") String companyUrl, @PathVariable(value = "id") Long id) {
		servicesService.deleteService(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("{companyUrl}/services/")
	public ResponseEntity<Services> saveService(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody Services service) {
		return new ResponseEntity<Services> (servicesService.saveService(service), HttpStatus.OK);
	}
	
	@PostMapping("{companyUrl}/services/texts/{position}")
	public ResponseEntity<Services> addServiceText(@PathVariable(value = "companyUrl") String companyUrl, @PathVariable(value = "position") int position, @RequestBody Services service) {
		return new ResponseEntity<Services> (servicesService.addNewText(position, service), HttpStatus.OK);
	}

	@GetMapping("{companyUrl}/website/")
	public ResponseEntity<Website> getWebsite(@PathVariable(value = "companyUrl") String companyUrl) {
		return new ResponseEntity<Website>(websiteService.getWebsite(), HttpStatus.OK);
	}

	@PostMapping("{companyUrl}/website/")
	public ResponseEntity<Website> updateWebsite(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody Website website) {
		return new ResponseEntity<Website>(websiteService.updateWebsite(website), HttpStatus.OK);
	}
	
	@PostMapping("{companyUrl}/website/pages") 
	public ResponseEntity<Website> createPage(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody Website website) {
		return new ResponseEntity<Website>(websiteService.createPage(website), HttpStatus.OK);
	}
	
	@PostMapping("{companyUrl}/website/pages/paragraph") 
	public ResponseEntity<Website> createParagraph(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody Page page) {
		return new ResponseEntity<Website>(websiteService.createParagraph(page), HttpStatus.OK);
	}

	@PostMapping("{companyUrl}/notify/email/")
	public ResponseEntity<Void> notifyByEmail(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody EmailDto emailDto) {
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("http://localhost:8200/api/v1/public/send/", emailDto, Void.class);
		return responseEntity;
	}

}
