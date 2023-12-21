package com.nephew.faqs.controllers;

import com.nephew.faqs.dtos.FAQsDto;
import com.nephew.faqs.entities.FAQs;
import com.nephew.faqs.services.DtoService;
import com.nephew.faqs.services.FAQsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

	@Autowired
	private DtoService dtoService;

	@Autowired
	private FAQsService faqsService;

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

}
