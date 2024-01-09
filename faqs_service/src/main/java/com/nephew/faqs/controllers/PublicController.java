package com.nephew.faqs.controllers;

import com.nephew.faqs.dtos.FAQsDto;
import com.nephew.faqs.entities.FAQs;
import com.nephew.faqs.services.DtoService;
import com.nephew.faqs.services.FAQsService;
import com.nephew.faqs.services.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

	@Autowired
	private DtoService dtoService;

	@Autowired
	private FAQsService faqsService;

	@Autowired
	private JdbcService jdbcService;

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

	// Example URL: http://localhost:8765/faqs-service/api/v1/public/npt/faqs/
	@PostMapping("{companyUrl}/faqs/")
	public ResponseEntity<Void> askNewFaq(@PathVariable(value = "companyUrl") String companyUrl, @RequestBody FAQs faqs) {
		jdbcService.insertFaq(faqs, companyUrl);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("{companyUrl}/faqs/")
	public ResponseEntity<ArrayList<FAQsDto>> getAllAnsweredQuestions(@PathVariable(value = "companyUrl") String companyUrl) throws SQLException {
		List<FAQs> faqsList = jdbcService.findAnsweredFaqsByCompanyUrl(companyUrl);
		ArrayList<FAQsDto> faqsDto = dtoService.convertFAQsDto(faqsList);
		return new ResponseEntity<>(faqsDto, HttpStatus.OK);
	}

}
