package com.nephew.faqs.controllers;

import com.nephew.faqs.entities.FAQs;
import com.nephew.faqs.services.FAQsService;
import com.nephew.faqs.services.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class PrivateController {

	@Autowired
	private FAQsService faqsService;

	@Autowired
	private JdbcService jdbcService;

	@GetMapping("{companyUrl}/faqs/")
	public ResponseEntity<List<FAQs>> getAllFaqsByCompanyUrl(@PathVariable(value = "companyUrl") String companyUrl) throws SQLException {
		return new ResponseEntity<>(jdbcService.findAllFaqsByCompanyUrl(companyUrl), HttpStatus.OK);
	}

	@DeleteMapping("{companyUrl}/faqs/{id}")
	public ResponseEntity<Void> deleteFaqById(@PathVariable(value = "companyUrl") String companyUrl, @PathVariable(value = "id") Long id) {
		jdbcService.deleteFaqById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("{companyUrl}/faqs/")
	public ResponseEntity<Void> saveFaq(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody FAQs faqs) {
		jdbcService.insertFaq(faqs, companyUrl);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
